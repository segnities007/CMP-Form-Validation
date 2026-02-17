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
import com.segnities007.cmp_form_validation.catalog.components.CatalogSection
import com.segnities007.cmp_form_validation.catalog.components.RuleChips
import com.segnities007.cmp_form_validation.validation.compose.ComposeValidatedField
import com.segnities007.cmp_form_validation.validation.compose.ValidationSupportingText
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import com.segnities007.cmp_form_validation.validation.maxLength
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.required
import kotlinx.collections.immutable.persistentListOf

@Composable
fun RuleSampleSection(
    title: String,
    description: String,
    placeholder: String,
    field: ComposeValidatedField<String>,
    chips: Array<String>,
) {
    CatalogSection(title = title, description = description) {
        RuleChips(*chips)
        OutlinedTextField(
            value = field.value,
            onValueChange = field::onValueChange,
            isError = field.showErrors && !field.result.isValid,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            label = { Text(title) },
            placeholder = { Text(placeholder) },
            supportingText = {
                ValidationSupportingText(
                    field = field,
                    idleText = "Type to validate",
                )
            },
        )
    }
}

@Preview
@Composable
private fun RuleSampleSectionPreview() {
    val field = rememberValidatedField(
        rules = persistentListOf(required(), minLength(3), maxLength(20)),
    )
    MaterialTheme {
        RuleSampleSection(
            title = "Nickname",
            description = "required + minLength(3) + maxLength(20)",
            placeholder = "your nickname",
            field = field,
            chips = arrayOf("required", "minLength(3)", "maxLength(20)"),
        )
    }
}
