package com.segnities007.cmp_form_validation.validation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot
import com.segnities007.cmp_form_validation.validation.Rule
import com.segnities007.cmp_form_validation.validation.ValidatedField
import com.segnities007.cmp_form_validation.validation.ValidationResult
import com.segnities007.cmp_form_validation.validation.ValidationStrategy
import com.segnities007.cmp_form_validation.validation.ValidationTrigger
import com.segnities007.cmp_form_validation.validation.Validator
import com.segnities007.cmp_form_validation.validation.validatorOf
import kotlinx.collections.immutable.ImmutableList

/**
 * Compose-friendly wrapper around [ValidatedField].
 *
 * Why this exists:
 * - [ValidatedField] is platform-agnostic and mutable, but not directly observable by Compose.
 * - This wrapper mirrors state via `mutableStateOf` so recomposition works naturally.
 *
 * State synchronization:
 * - All mutable state properties are updated atomically inside [Snapshot.withMutableSnapshot]
 *   to prevent recomposition from reading a partially-updated intermediate state.
 */
@Stable
class ComposeValidatedField<T> internal constructor(
    private val delegate: ValidatedField<T>,
) {
    var value: T by mutableStateOf(delegate.value)
        private set

    var result: ValidationResult by mutableStateOf(delegate.result)
        private set

    var touched: Boolean by mutableStateOf(delegate.touched)
        private set

    var blurred: Boolean by mutableStateOf(delegate.blurred)
        private set

    var submitted: Boolean by mutableStateOf(delegate.submitted)
        private set

    val dirty: Boolean
        get() = delegate.dirty

    val showErrors: Boolean
        get() = delegate.showErrors

    /** Updates value and applies trigger-aware validation. */
    fun onValueChange(next: T) {
        delegate.onValueChange(next)
        sync()
    }

    /** Marks blur interaction and applies trigger-aware validation. */
    fun onBlur() {
        delegate.onBlur()
        sync()
    }

    /** Runs validation immediately. */
    fun validate(): ValidationResult = runAndSync(delegate::validate)

    /** Marks submit interaction and validates immediately. */
    fun submit(): ValidationResult = runAndSync(delegate::submit)

    /** Resets value and interaction flags. */
    fun reset() {
        delegate.reset()
        sync()
    }

    /**
     * Atomically synchronizes all observable state from the delegate.
     *
     * Using [Snapshot.withMutableSnapshot] ensures that Compose never observes
     * a half-updated state (e.g. `value` changed but `result` not yet).
     */
    private fun sync() {
        Snapshot.withMutableSnapshot {
            value = delegate.value
            result = delegate.result
            touched = delegate.touched
            blurred = delegate.blurred
            submitted = delegate.submitted
        }
    }

    private inline fun runAndSync(action: () -> ValidationResult): ValidationResult {
        val validated = action()
        sync()
        return validated
    }
}

/**
 * Remembers a compose-aware field from an existing [Validator].
 *
 * Use this overload when your validator is created elsewhere and reused.
 */
@Composable
fun <T> rememberValidatedField(
    initialValue: T,
    validator: Validator<T>,
    trigger: ValidationTrigger = ValidationTrigger.OnSubmitThenChange,
): ComposeValidatedField<T> = remember(initialValue, validator, trigger) {
    ComposeValidatedField(ValidatedField(initialValue, validator, trigger))
}

/**
 * Remembers a compose-aware string field from inline [rules].
 *
 * This is the primary integration entrypoint for most screens.
 */
@Composable
fun rememberValidatedField(
    initialValue: String = "",
    trigger: ValidationTrigger = ValidationTrigger.OnSubmitThenChange,
    strategy: ValidationStrategy = ValidationStrategy.CollectAll,
    vararg rules: Rule<String>,
): ComposeValidatedField<String> {
    val validator = remember(strategy, *rules) { validatorOf(strategy, *rules) }
    return rememberValidatedField(
        initialValue = initialValue,
        validator = validator,
        trigger = trigger,
    )
}

/**
 * List-based overload of [rememberValidatedField] for call-sites that prefer non-vararg APIs.
 */
@Composable
fun rememberValidatedField(
    initialValue: String = "",
    trigger: ValidationTrigger = ValidationTrigger.OnSubmitThenChange,
    strategy: ValidationStrategy = ValidationStrategy.CollectAll,
    rules: ImmutableList<Rule<String>>,
): ComposeValidatedField<String> = rememberValidatedField(
    initialValue = initialValue,
    trigger = trigger,
    strategy = strategy,
    *rules.toTypedArray(),
)
