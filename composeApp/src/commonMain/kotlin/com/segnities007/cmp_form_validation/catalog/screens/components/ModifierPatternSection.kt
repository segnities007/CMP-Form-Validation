package com.segnities007.cmp_form_validation.catalog.screens.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.catalog.components.CatalogSection
import com.segnities007.cmp_form_validation.catalog.components.RuleChips
import com.segnities007.cmp_form_validation.validation.compose.ComposeValidatedField
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
        CatalogValidatedOutlinedField(
            label = "Password",
            idleText = "Blur to trigger validation",
            field = field,
            modifier =
                Modifier
                    .validation(field),
            visualTransformation = PasswordVisualTransformation(),
        )
    }
}

@Preview
@Composable
private fun ModifierPatternSectionPreview() {
    val field =
        rememberValidatedField(
            initialValue = "",
            rules = persistentListOf(required(), minLength(8)),
        )
    MaterialTheme {
        ModifierPatternSection(field = field)
    }
}
