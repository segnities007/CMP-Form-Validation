package com.segnities007.cmp_form_validation.catalog.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.validation.compose.ComposeValidatedField
import com.segnities007.cmp_form_validation.validation.compose.ValidationSupportingText

@Composable
internal fun CatalogValidatedOutlinedField(
    label: String,
    idleText: String,
    field: ComposeValidatedField<String>,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeholderText: String? = null,
    onValueChange: (String) -> Unit = field::onValueChange,
) {
    OutlinedTextField(
        value = field.value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        label = { Text(label) },
        placeholder = {
            if (placeholderText != null) {
                Text(placeholderText)
            }
        },
        isError = field.showErrors && !field.result.isValid,
        visualTransformation = visualTransformation,
        supportingText = { ValidationSupportingText(field, idleText = idleText) },
    )
}
