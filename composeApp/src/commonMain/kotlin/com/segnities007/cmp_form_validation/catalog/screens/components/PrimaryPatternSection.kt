package com.segnities007.cmp_form_validation.catalog.screens.components

import androidx.compose.runtime.Composable
import com.segnities007.cmp_form_validation.catalog.components.CatalogSection
import com.segnities007.cmp_form_validation.catalog.components.PrimaryBadge
import com.segnities007.cmp_form_validation.catalog.components.RuleChips
import com.segnities007.cmp_form_validation.validation.compose.ComposeValidatedField

@Composable
fun PrimaryPatternSection(field: ComposeValidatedField<String>) {
    CatalogSection(
        title = "Primary (Recommended)",
        description = "rememberValidatedField + native OutlinedTextField. Compose state is explicit.",
    ) {
        PrimaryBadge("Primary")
        RuleChips("rememberValidatedField", "OutlinedTextField")
        CatalogValidatedOutlinedField(
            label = "Email",
            idleText = "Use this as default style",
            field = field,
        )
    }
}
