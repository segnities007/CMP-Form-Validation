package com.segnities007.cmp_form_validation.catalog.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FormActionButtons(
    onValidate: () -> Unit,
    onReset: () -> Unit,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(onClick = onValidate) {
            Text("Validate")
        }
        Button(onClick = onReset) {
            Text("Reset")
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
