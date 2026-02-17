package com.segnities007.cmp_form_validation.validation

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap

/**
 * A machine-readable validation failure.
 *
 * Why this exists:
 * - `code` lets UI/application layers map failures to localized messages.
 * - `defaultMessage` provides a safe fallback when no mapper is available.
 * - `path` and `meta` support richer diagnostics without coupling to a UI toolkit.
 *
 * Typical usage:
 * - Produced by a [Rule] when validation fails.
 * - Collected in [ValidationResult.errors].
 */
data class ValidationError(
    val code: String,
    val defaultMessage: String,
    val path: String? = null,
    val meta: ImmutableMap<String, String> = persistentMapOf(),
)

private val EmptyValidationErrors = persistentListOf<ValidationError>()

/**
 * Immutable result of validating one input value.
 *
 * Why this exists:
 * - Returning a rich object is easier to compose and test than booleans.
 * - Callers can inspect all failures or only the first one depending on strategy.
 */
data class ValidationResult(
    val errors: ImmutableList<ValidationError> = EmptyValidationErrors,
) {
    /** `true` when [errors] is empty. */
    val isValid: Boolean = errors.isEmpty()

    companion object {
        /** Creates a successful result without errors. */
        fun valid(): ValidationResult = ValidationResult(EmptyValidationErrors)

        /** Creates a failed result from the provided [errors]. */
        fun invalid(errors: Iterable<ValidationError>): ValidationResult =
            ValidationResult(errors.toList().toImmutableList())
    }
}

/**
 * Smallest validation unit.
 *
 * A rule inspects a value and either:
 * - returns `null` when the value is valid for that rule
 * - returns a [ValidationError] when invalid
 */
fun interface Rule<T> {
    fun validate(value: T): ValidationError?
}

/**
 * Applies multiple [Rule] instances to a value.
 *
 * Use [ValidationStrategy.CollectAll] to gather all failures, or
 * [ValidationStrategy.FirstError] to short-circuit for performance.
 */
class Validator<T>(
    private val rules: List<Rule<T>>,
    private val strategy: ValidationStrategy = ValidationStrategy.CollectAll,
) {
    /** Validates [value] using this validator's rule list and strategy. */
    fun validate(value: T): ValidationResult {
        val errors = mutableListOf<ValidationError>()
        for (rule in rules) {
            val error = rule.validate(value) ?: continue
            errors += error
            if (strategy == ValidationStrategy.FirstError) {
                break
            }
        }
        return ValidationResult(errors.toImmutableList())
    }

    /** Returns a new validator with [rule] appended. */
    operator fun plus(rule: Rule<T>): Validator<T> = Validator(rules + rule, strategy)
}

/** Builds a [Validator] using [ValidationStrategy.CollectAll]. */
fun <T> validatorOf(vararg rules: Rule<T>): Validator<T> = Validator(rules.toList())

/** Builds a [Validator] using an explicit [strategy]. */
fun <T> validatorOf(strategy: ValidationStrategy, vararg rules: Rule<T>): Validator<T> =
    Validator(rules.toList(), strategy)

/**
 * Execution strategy used by [Validator].
 *
 * - [CollectAll]: evaluates all rules and returns every error.
 * - [FirstError]: stops after the first failure.
 */
enum class ValidationStrategy {
    CollectAll,
    FirstError,
}

/** Standard error codes used by built-in rules. */
object ErrorCode {
    const val REQUIRED = "required"
    const val MIN_LENGTH = "min_length"
    const val MAX_LENGTH = "max_length"
    const val PATTERN = "pattern"
    const val EMAIL = "email"
}

private fun validationError(
    code: String,
    defaultMessage: String,
    path: String? = null,
    meta: Map<String, String> = emptyMap(),
): ValidationError = ValidationError(
    code = code,
    defaultMessage = defaultMessage,
    path = path,
    meta = meta.toImmutableMap(),
)

/**
 * Validates that a string is not blank.
 *
 * @param trim if `true`, surrounding whitespace is ignored.
 * @param message fallback error message used when validation fails.
 */
fun required(
    trim: Boolean = true,
    message: String = "This field is required.",
): Rule<String> = Rule { value ->
    val candidate = if (trim) value.trim() else value
    if (candidate.isEmpty()) {
        validationError(
            code = ErrorCode.REQUIRED,
            defaultMessage = message,
        )
    } else {
        null
    }
}

/**
 * Validates that string length is greater than or equal to [min].
 *
 * @throws IllegalArgumentException when [min] is negative.
 */
fun minLength(
    min: Int,
    message: String = "Must be at least $min characters.",
): Rule<String> {
    require(min >= 0) { "min must be >= 0" }
    return Rule { value ->
        if (value.length < min) {
            validationError(
                code = ErrorCode.MIN_LENGTH,
                defaultMessage = message,
                meta = mapOf("min" to min.toString()),
            )
        } else {
            null
        }
    }
}

/**
 * Validates that string length is less than or equal to [max].
 *
 * @throws IllegalArgumentException when [max] is negative.
 */
fun maxLength(
    max: Int,
    message: String = "Must be $max characters or less.",
): Rule<String> {
    require(max >= 0) { "max must be >= 0" }
    return Rule { value ->
        if (value.length > max) {
            validationError(
                code = ErrorCode.MAX_LENGTH,
                defaultMessage = message,
                meta = mapOf("max" to max.toString()),
            )
        } else {
            null
        }
    }
}

/**
 * Validates that a string matches [regex].
 *
 * **Security note**: Avoid using complex regular expressions with nested quantifiers
 * (e.g. `(a+)+`) as they can cause catastrophic backtracking (ReDoS).
 * Consider combining with [maxLength] to limit input size before regex evaluation.
 *
 * @param code custom error code to expose when matching fails.
 * @param message fallback error message.
 */
fun pattern(
    regex: Regex,
    code: String = ErrorCode.PATTERN,
    message: String = "Invalid format.",
): Rule<String> = Rule { value ->
    if (regex.matches(value)) {
        null
    } else {
        validationError(
            code = code,
            defaultMessage = message,
            meta = mapOf("regex" to regex.pattern),
        )
    }
}

/**
 * Maximum input length accepted by [email]. Inputs exceeding this length are
 * rejected early to prevent catastrophic backtracking in the regex engine.
 */
private const val EMAIL_MAX_LENGTH = 320

/**
 * Pragmatic email regex designed to resist backtracking.
 *
 * Structure:
 * - Local part: `[A-Za-z0-9+_.-]+` — one or more allowed characters (no nested quantifiers).
 * - Domain labels: `[A-Za-z0-9-]+` separated by `.` — possessive-style matching via atomic groups
 *   is not available in all KMP targets, so we instead guard with a length check.
 * - TLD: `[A-Za-z]{2,}` — at least two alpha characters.
 */
private val safeEmailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$")

/**
 * Validates an email with a pragmatic (not RFC-complete) regex.
 *
 * **Limitations**:
 * - Only ASCII email addresses are supported.
 * - Internationalized domain names (IDN) and non-ASCII local parts are rejected.
 * - Input length is capped at [EMAIL_MAX_LENGTH] (320 chars) to mitigate ReDoS.
 *
 * Products with stricter requirements can provide a custom [pattern] rule.
 */
fun email(
    message: String = "Invalid email address.",
): Rule<String> = Rule { value ->
    if (value.length <= EMAIL_MAX_LENGTH && safeEmailRegex.matches(value)) {
        null
    } else {
        validationError(
            code = ErrorCode.EMAIL,
            defaultMessage = message,
        )
    }
}
