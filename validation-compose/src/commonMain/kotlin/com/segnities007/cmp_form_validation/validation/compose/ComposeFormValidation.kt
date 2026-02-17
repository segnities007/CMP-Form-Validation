package com.segnities007.cmp_form_validation.validation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.segnities007.cmp_form_validation.validation.FormRule
import com.segnities007.cmp_form_validation.validation.ValidationError
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap

/**
 * Compose-facing form coordinator for multiple [ComposeValidatedField] values.
 *
 * It runs:
 * - field-level validation via each field's own [ComposeValidatedField.submit]
 * - form-level validation via [FormRule]
 */
@Stable
class ComposeValidatedStringForm internal constructor(
    private val fields: ImmutableMap<String, ComposeValidatedField<String>>,
    private val formRules: ImmutableList<FormRule>,
) {
    /** Latest cross-field errors produced by [formRules]. */
    var formErrors: ImmutableList<ValidationError> by mutableStateOf(persistentListOf())
        private set

    /** Returns the latest values snapshot. */
    fun values(): ImmutableMap<String, String> = fields.mapValues { (_, field) -> field.value }.toImmutableMap()

    /**
     * Submits all fields and returns overall validity.
     *
     * Returns `true` only when all fields and all form rules are valid.
     */
    fun submit(): Boolean {
        val allValid = fields.values.all { it.submit().isValid }
        formErrors = evaluateFormRules()
        return allValid && formErrors.isEmpty()
    }

    /** Re-runs only cross-field rules. */
    fun revalidateCrossField(): ImmutableList<ValidationError> {
        formErrors = evaluateFormRules()
        return formErrors
    }

    /** Resets all fields and clears cross-field errors. */
    fun reset() {
        fields.values.forEach { it.reset() }
        formErrors = persistentListOf()
    }

    private fun evaluateFormRules(): ImmutableList<ValidationError> {
        val currentValues = values()
        return formRules.mapNotNull { it.validate(currentValues) }.toImmutableList()
    }
}

/**
 * Remembers a compose-friendly form coordinator.
 *
 * Use this when your screen has multiple string fields and optional form-level rules.
 */
@Composable
fun rememberValidatedStringForm(
    fields: ImmutableMap<String, ComposeValidatedField<String>>,
    formRules: ImmutableList<FormRule> = persistentListOf(),
): ComposeValidatedStringForm = remember(fields, formRules) {
    ComposeValidatedStringForm(
        fields = fields,
        formRules = formRules,
    )
}
