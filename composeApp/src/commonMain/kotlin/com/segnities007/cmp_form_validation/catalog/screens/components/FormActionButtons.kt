package com.segnities007.cmp_form_validation.catalog.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.catalog.components.CatalogTokens

private const val VALIDATE_LABEL = "Validate"
private const val RESET_LABEL = "Reset"

@Composable
fun FormActionButtons(
    onValidate: () -> Unit,
    onReset: () -> Unit,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(CatalogTokens.ActionButtonSpacing)) {
        Button(onClick = onValidate) {
            Text(VALIDATE_LABEL)
        }
        Button(onClick = onReset) {
            Text(RESET_LABEL)
        }
    }
}

@Preview
@Composable
private fun FormActionButtonsPreview() {
    MaterialTheme {
        FormActionButtons(onValidate = {}, onReset = {})
    }
}
