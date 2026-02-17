package com.segnities007.cmp_form_validation.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.catalog.components.CatalogHero
import com.segnities007.cmp_form_validation.catalog.components.CatalogSection
import com.segnities007.cmp_form_validation.catalog.components.PrimaryBadge
import com.segnities007.cmp_form_validation.catalog.components.RuleChips
import com.segnities007.cmp_form_validation.validation.email
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.required
import com.segnities007.cmp_form_validation.validation.compose.ValidatedOutlinedTextField
import com.segnities007.cmp_form_validation.validation.compose.ValidationSupportingText
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import com.segnities007.cmp_form_validation.validation.compose.validation
import kotlinx.collections.immutable.persistentListOf

/** Catalog screen comparing the three integration styles (primary + supplementary). */
@Composable
fun PatternCatalogScreen(innerPadding: PaddingValues) {
    val primaryField = rememberValidatedField(
        initialValue = "",
        rules = persistentListOf(required(), email()),
    )
    val modifierField = rememberValidatedField(
        initialValue = "",
        rules = persistentListOf(required(), minLength(8)),
    )
    val wrapperField = rememberValidatedField(
        initialValue = "",
        rules = persistentListOf(required(), minLength(8)),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            CatalogHero(
                title = "Integration Patterns",
                subtitle = "Primary approach + two supplementary options.",
            )
        }
        item {
            CatalogSection(
                title = "Primary (Recommended)",
                description = "rememberValidatedField + native OutlinedTextField. Compose state is explicit.",
            ) {
                PrimaryBadge("Primary")
                RuleChips("rememberValidatedField", "OutlinedTextField")
                OutlinedTextField(
                    value = primaryField.value,
                    onValueChange = primaryField::onValueChange,
                    isError = primaryField.showErrors && !primaryField.result.isValid,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    label = { Text("Email") },
                    supportingText = {
                        ValidationSupportingText(primaryField, idleText = "Use this as default style")
                    },
                )
            }
        }

        item {
            CatalogSection(
                title = "Modifier-based (Supplementary)",
                description = "Attach blur validation behavior without replacing your field component.",
            ) {
                RuleChips("Modifier.validation", "Blur trigger")
                OutlinedTextField(
                    value = modifierField.value,
                    onValueChange = modifierField::onValueChange,
                    isError = modifierField.showErrors && !modifierField.result.isValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .validation(modifierField),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    label = { Text("Password") },
                    supportingText = {
                        ValidationSupportingText(modifierField, idleText = "Blur to trigger validation")
                    },
                )
            }
        }

        item {
            CatalogSection(
                title = "Wrapper-based (Supplementary)",
                description = "Use a ready-made field wrapper for compact code.",
            ) {
                RuleChips("ValidatedOutlinedTextField", "Boilerplate reduction")
                ValidatedOutlinedTextField(
                    field = wrapperField,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = { Text("Password") },
                    idleSupportingText = "Good for rapid screen construction",
                )
            }
        }
    }
}

@Preview
@Composable
private fun PatternCatalogScreenPreview() {
    MaterialTheme {
        PatternCatalogScreen(PaddingValues())
    }
}
