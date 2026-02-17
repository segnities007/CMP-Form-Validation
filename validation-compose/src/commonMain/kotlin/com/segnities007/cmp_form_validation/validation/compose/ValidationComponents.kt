package com.segnities007.cmp_form_validation.validation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

/**
 * Renders validation messages for a [ComposeValidatedField].
 *
 * Why this exists:
 * - Keeps message rendering consistent across screens.
 * - Separates "how to display errors" from field declarations.
 *
 * @param maxErrors limits how many errors are shown.
 * @param idleText optional helper text shown before errors become visible.
 * @param validText text shown when the field is valid. Defaults to null (no text shown).
 */
@Composable
fun ValidationSupportingText(
    field: ComposeValidatedField<*>,
    modifier: Modifier = Modifier,
    maxErrors: Int = 1,
    idleText: String? = null,
    validText: String? = null,
) {
    if (!field.showErrors) {
        if (idleText != null) {
            SupportingMessage(
                text = idleText,
                modifier = modifier,
            )
        }
        return
    }

    if (field.result.isValid) {
        if (validText != null) {
            SupportingMessage(
                text = validText,
                modifier = modifier,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        return
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        field.result.errors.take(maxErrors).forEach { error ->
            SupportingMessage(
                text = error.defaultMessage,
                color = MaterialTheme.colorScheme.error,
            )
        }
    }
}

@Composable
private fun SupportingMessage(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.bodySmall,
    )
}

/**
 * Convenience wrapper around Material3 `OutlinedTextField` with built-in validation wiring.
 *
 * This API is intentionally supplementary.
 * Primary recommendation is still:
 * - `rememberValidatedField` + native `OutlinedTextField`.
 */
@Composable
fun ValidatedOutlinedTextField(
    field: ComposeValidatedField<String>,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    idleSupportingText: String? = null,
    validSupportingText: String? = null,
    maxDisplayedErrors: Int = 1,
) {
    OutlinedTextField(
        value = field.value,
        onValueChange = field::onValueChange,
        modifier = modifier.validation(field),
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        isError = field.showErrors && !field.result.isValid,
        label = label,
        placeholder = placeholder,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        supportingText = {
            ValidationSupportingText(
                field = field,
                idleText = idleSupportingText,
                validText = validSupportingText,
                maxErrors = maxDisplayedErrors,
            )
        },
    )
}
