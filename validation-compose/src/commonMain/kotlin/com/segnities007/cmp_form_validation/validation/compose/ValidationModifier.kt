package com.segnities007.cmp_form_validation.validation.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged

/**
 * Adds validation-focused behavior to an existing modifier chain.
 *
 * Why this exists:
 * - Lets existing fields opt into blur-trigger validation with minimal code changes.
 *
 * How to use:
 * - Attach to `modifier` of a text field-like composable.
 * - Keep this as a supplementary integration style; primary style is explicit field wiring.
 */
fun Modifier.validation(
    field: ComposeValidatedField<*>,
    validateOnBlur: Boolean = true,
): Modifier = composed {
    var wasFocused by remember { mutableStateOf(false) }
    this.onFocusChanged { focusState ->
        if (validateOnBlur && wasFocused && !focusState.isFocused) {
            field.onBlur()
        }
        wasFocused = focusState.isFocused
    }
}
