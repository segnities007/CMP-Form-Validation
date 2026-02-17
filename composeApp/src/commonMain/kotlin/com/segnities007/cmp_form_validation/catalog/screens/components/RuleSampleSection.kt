package com.segnities007.cmp_form_validation.catalog.screens.components

import androidx.compose.runtime.Composable
import com.segnities007.cmp_form_validation.catalog.components.CatalogSection
import com.segnities007.cmp_form_validation.catalog.components.RuleChips
import com.segnities007.cmp_form_validation.validation.compose.ComposeValidatedField

@Composable
fun RuleSampleSection(
    title: String,
    description: String,
    placeholder: String,
    field: ComposeValidatedField<String>,
    vararg chips: String,
) {
    CatalogSection(title = title, description = description) {
        RuleChips(*chips)
        CatalogValidatedOutlinedField(
            label = title,
            idleText = "Type to validate",
            field = field,
            placeholderText = placeholder,
        )
    }
}
