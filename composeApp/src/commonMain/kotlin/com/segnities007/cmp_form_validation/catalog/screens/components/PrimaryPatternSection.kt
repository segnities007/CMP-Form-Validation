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
import com.segnities007.cmp_form_validation.catalog.components.PrimaryBadge
import com.segnities007.cmp_form_validation.catalog.components.RuleChips
import com.segnities007.cmp_form_validation.validation.compose.ComposeValidatedField
import com.segnities007.cmp_form_validation.validation.compose.ValidationSupportingText
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import com.segnities007.cmp_form_validation.validation.email
import com.segnities007.cmp_form_validation.validation.required
import kotlinx.collections.immutable.persistentListOf

@Composable
fun PrimaryPatternSection(field: ComposeValidatedField<String>) {
    CatalogSection(
        title = "Primary (Recommended)",
        description = "rememberValidatedField + native OutlinedTextField. Compose state is explicit.",
    ) {
        PrimaryBadge("Primary")
        RuleChips("rememberValidatedField", "OutlinedTextField")
        OutlinedTextField(
            value = field.value,
            onValueChange = field::onValueChange,
            isError = field.showErrors && !field.result.isValid,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            label = { Text("Email") },
            supportingText = {
                ValidationSupportingText(field, idleText = "Use this as default style")
            },
        )
    }
}

@Preview
@Composable
private fun PrimaryPatternSectionPreview() {
    val field = rememberValidatedField(
        initialValue = "",
        rules = persistentListOf(required(), email()),
    )
    MaterialTheme {
        PrimaryPatternSection(field = field)
    }
}
