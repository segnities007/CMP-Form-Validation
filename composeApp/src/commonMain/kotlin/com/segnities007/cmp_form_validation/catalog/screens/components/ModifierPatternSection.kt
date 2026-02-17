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
import com.segnities007.cmp_form_validation.validation.compose.validation
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.required
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ModifierPatternSection(field: ComposeValidatedField<String>) {
    CatalogSection(
        title = "Modifier-based (Supplementary)",
        description = "Attach blur validation behavior without replacing your field component.",
    ) {
        RuleChips("Modifier.validation", "Blur trigger")
        OutlinedTextField(
            value = field.value,
            onValueChange = field::onValueChange,
            isError = field.showErrors && !field.result.isValid,
            modifier = Modifier
                .fillMaxWidth()
                .validation(field),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            label = { Text("Password") },
            supportingText = {
                ValidationSupportingText(field, idleText = "Blur to trigger validation")
            },
        )
    }
}

@Preview
@Composable
private fun ModifierPatternSectionPreview() {
    val field = rememberValidatedField(
        initialValue = "",
        rules = persistentListOf(required(), minLength(8)),
    )
    MaterialTheme {
        ModifierPatternSection(field = field)
    }
}
