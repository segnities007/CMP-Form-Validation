package com.segnities007.cmp_form_validation.catalog.screens.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.validation.ValidationError
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

private const val FORM_VALID_MESSAGE = "Form is valid. Ready to submit."
private const val FORM_INVALID_MESSAGE = "Form has validation errors."
private val FormValidColor = Color(0xFF1B5E20)

@Composable
fun FormCrossFieldErrors(errors: ImmutableList<ValidationError>) {
    errors.forEach { error ->
        Text(
            text = error.defaultMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun FormSubmissionStatus(isFormValid: Boolean) {
    Text(
        text = if (isFormValid) FORM_VALID_MESSAGE else FORM_INVALID_MESSAGE,
        color = if (isFormValid) FormValidColor else MaterialTheme.colorScheme.error,
    )
}

@Preview
@Composable
private fun FormCrossFieldErrorsPreview() {
    MaterialTheme {
        FormCrossFieldErrors(
            errors = persistentListOf(
                ValidationError(
                    code = "password_mismatch",
                    defaultMessage = "Password confirmation does not match.",
                ),
            ),
        )
    }
}

@Preview
@Composable
private fun FormSubmissionStatusPreview() {
    MaterialTheme {
        FormSubmissionStatus(isFormValid = false)
    }
}
