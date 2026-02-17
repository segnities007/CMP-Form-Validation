package com.segnities007.cmp_form_validation.validation

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap

private val EmptyFormErrors = persistentListOf<ValidationError>()

/**
 * Defines when a field should run validation and reveal errors.
 *
 * - [OnChange]: validate as the value changes.
 * - [OnBlur]: validate when focus leaves the field.
 * - [OnSubmitThenChange]: validate on submit, then on later edits.
 */
enum class ValidationTrigger {
    OnChange,
    OnBlur,
    OnSubmitThenChange,
}

/**
 * Stateful field model that owns value, interaction flags, and validation result.
 *
 * Why this exists:
 * - Encapsulates field behavior so UI code stays declarative.
 * - Keeps trigger policy centralized and testable.
 *
 * How to use:
 * 1. Call [onValueChange] from text/input callbacks.
 * 2. Call [onBlur] from focus-loss callbacks when needed.
 * 3. Call [submit] when the form is submitted.
 * 4. Read [showErrors] and [result] to render UI.
 *
 * **Thread safety**: This class is **not** thread-safe. All methods must be called
 * from the same thread (typically the main/UI thread). If you need to access
 * a [ValidatedField] from a background thread, use appropriate synchronization
 * or wrap access in a `Dispatchers.Main` coroutine.
 */
class ValidatedField<T>(
    private val initialValue: T,
    private val validator: Validator<T>,
    private val trigger: ValidationTrigger = ValidationTrigger.OnSubmitThenChange,
) {
    var value: T = initialValue
        private set

    var touched: Boolean = false
        private set

    var blurred: Boolean = false
        private set

    var submitted: Boolean = false
        private set

    var result: ValidationResult = ValidationResult.valid()
        private set

    val dirty: Boolean
        get() = value != initialValue

    /**
     * Whether the UI should currently display validation errors.
     *
     * This follows [trigger] and interaction state.
     */
    val showErrors: Boolean
        get() =
            when (trigger) {
                ValidationTrigger.OnChange -> touched || dirty
                ValidationTrigger.OnBlur -> blurred
                ValidationTrigger.OnSubmitThenChange -> submitted
            }

    /** Updates value and validates depending on [trigger]. */
    fun onValueChange(next: T) {
        value = next
        touched = true
        if (shouldValidateOnValueChange()) {
            validate()
        }
    }

    /** Marks the field as blurred and validates depending on [trigger]. */
    fun onBlur() {
        touched = true
        blurred = true
        if (shouldValidateOnBlur()) {
            validate()
        }
    }

    /** Runs validation against the current value. */
    fun validate(): ValidationResult {
        result = validator.validate(value)
        return result
    }

    /** Marks submit state and validates immediately. */
    fun submit(): ValidationResult {
        submitted = true
        touched = true
        blurred = true
        return validate()
    }

    /** Resets value, interaction flags, and validation state. */
    fun reset() {
        value = initialValue
        touched = false
        blurred = false
        submitted = false
        result = ValidationResult.valid()
    }

    private fun shouldValidateOnValueChange(): Boolean =
        when (trigger) {
            ValidationTrigger.OnChange -> true
            ValidationTrigger.OnBlur -> false
            ValidationTrigger.OnSubmitThenChange -> submitted
        }

    private fun shouldValidateOnBlur(): Boolean =
        when (trigger) {
            ValidationTrigger.OnChange -> false
            ValidationTrigger.OnBlur -> true
            ValidationTrigger.OnSubmitThenChange -> submitted
        }
}

/**
 * Form-level validation rule.
 *
 * Unlike [Rule], this rule can inspect multiple field values at once.
 */
fun interface FormRule {
    fun validate(values: ImmutableMap<String, String>): ValidationError?
}

/** Snapshot returned by [ValidatedStringForm.submit]. */
data class FormValidationSnapshot(
    val isValid: Boolean,
    val fieldResults: ImmutableMap<String, ValidationResult>,
    val formErrors: ImmutableList<ValidationError>,
)

/**
 * String-focused form model that coordinates field and cross-field validation.
 *
 * Why string-based:
 * - Most UI inputs are strings at the entry boundary.
 * - It keeps v1 simple while leaving room for typed form models later.
 *
 * **Thread safety**: This class is **not** thread-safe. See [ValidatedField] for details.
 */
class ValidatedStringForm(
    private val fields: ImmutableMap<String, ValidatedField<String>>,
    private val formRules: List<FormRule> = emptyList(),
) {
    /** Latest cross-field validation errors. */
    var formErrors: ImmutableList<ValidationError> = EmptyFormErrors
        private set

    /** Current field values keyed by field name. */
    fun values(): ImmutableMap<String, String> = fields.currentValues()

    /** Returns the latest result for a named field. */
    fun fieldResult(name: String): ValidationResult = fields.getValue(name).result

    /**
     * Submits the form:
     * - submits and validates all fields
     * - evaluates all form-level rules
     */
    fun submit(): FormValidationSnapshot {
        val fieldResults = fields.mapValues { (_, field) -> field.submit() }.toImmutableMap()
        val crossFieldErrors = evaluateFormRules()
        return FormValidationSnapshot(
            isValid = fieldResults.values.all { it.isValid } && crossFieldErrors.isEmpty(),
            fieldResults = fieldResults,
            formErrors = crossFieldErrors,
        )
    }

    /** Re-runs only form-level rules and returns updated cross-field errors. */
    fun revalidateCrossField(): ImmutableList<ValidationError> {
        return evaluateFormRules()
    }

    /** Resets all fields and clears form-level errors. */
    fun reset() {
        fields.values.forEach { it.reset() }
        formErrors = EmptyFormErrors
    }

    private fun evaluateFormRules(): ImmutableList<ValidationError> {
        val currentValues = values()
        val crossFieldErrors = formRules.mapNotNull { it.validate(currentValues) }.toImmutableList()
        formErrors = crossFieldErrors
        return crossFieldErrors
    }
}

private fun ImmutableMap<String, ValidatedField<String>>.currentValues(): ImmutableMap<String, String> =
    mapValues { (_, field) -> field.value }.toImmutableMap()

/**
 * Creates a simple cross-field equality rule.
 *
 * Common use case:
 * - password and password-confirmation matching.
 *
 * @param leftField name of the first field to compare.
 * @param rightField name of the second field to compare.
 * @throws IllegalArgumentException if either field name is not present in the values map.
 */
fun fieldsMatchRule(
    leftField: String,
    rightField: String,
    code: String = "field_mismatch",
    message: String = "Fields do not match.",
): FormRule =
    FormRule { values ->
        val left =
            requireNotNull(values[leftField]) {
                "fieldsMatchRule: field '$leftField' not found. Available fields: ${values.keys}"
            }
        val right =
            requireNotNull(values[rightField]) {
                "fieldsMatchRule: field '$rightField' not found. Available fields: ${values.keys}"
            }
        if (left == right) {
            null
        } else {
            ValidationError(
                code = code,
                defaultMessage = message,
                path = leftField,
                meta = mapOf("leftField" to leftField, "rightField" to rightField).toImmutableMap(),
            )
        }
    }
