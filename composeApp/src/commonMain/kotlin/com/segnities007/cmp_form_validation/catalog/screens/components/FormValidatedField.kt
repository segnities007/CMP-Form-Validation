package com.segnities007.cmp_form_validation.catalog.screens.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.validation.compose.ComposeValidatedField
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import com.segnities007.cmp_form_validation.validation.maxLength
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.required
import kotlinx.collections.immutable.persistentListOf

@Composable
fun FormValidatedField(
    label: String,
    idleText: String,
    field: ComposeValidatedField<String>,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onEdited: () -> Unit,
) {
    CatalogValidatedOutlinedField(
        label = label,
        idleText = idleText,
        field = field,
        visualTransformation = visualTransformation,
        onValueChange = {
            field.onValueChange(it)
            onEdited()
        },
    )
}

@Preview
@Composable
private fun FormValidatedFieldPreview() {
    val field =
        rememberValidatedField(
            rules = persistentListOf(required(), minLength(2), maxLength(30)),
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
