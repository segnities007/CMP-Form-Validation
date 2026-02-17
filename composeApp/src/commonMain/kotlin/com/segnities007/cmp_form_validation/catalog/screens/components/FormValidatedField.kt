package com.segnities007.cmp_form_validation.catalog.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.validation.compose.ComposeValidatedField
import com.segnities007.cmp_form_validation.validation.compose.ValidationSupportingText
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.required
import kotlinx.collections.immutable.persistentListOf

@Composable
fun FormValidatedField(
    label: String,
    idleText: String,
    field: ComposeValidatedField<String>,
    onEdited: () -> Unit,
) {
    OutlinedTextField(
        value = field.value,
        onValueChange = {
            field.onValueChange(it)
            onEdited()
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        label = { Text(label) },
        isError = field.showErrors && !field.result.isValid,
        supportingText = { ValidationSupportingText(field, idleText = idleText) },
    )
}

@Preview
@Composable
private fun FormValidatedFieldPreview() {
    val field = rememberValidatedField(
        rules = persistentListOf(required(), minLength(2)),
    )
    MaterialTheme {
        FormValidatedField(
            label = "Name",
            idleText = "2 to 30 chars",
            field = field,
            onEdited = {},
        )
    }
}
