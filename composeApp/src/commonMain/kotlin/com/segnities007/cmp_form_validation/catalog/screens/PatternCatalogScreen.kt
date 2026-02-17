package com.segnities007.cmp_form_validation.catalog.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.catalog.components.CatalogHero
import com.segnities007.cmp_form_validation.catalog.components.CatalogLazyColumn
import com.segnities007.cmp_form_validation.catalog.screens.components.ModifierPatternSection
import com.segnities007.cmp_form_validation.catalog.screens.components.PrimaryPatternSection
import com.segnities007.cmp_form_validation.catalog.screens.components.WrapperPatternSection
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import com.segnities007.cmp_form_validation.validation.email
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.required
import kotlinx.collections.immutable.persistentListOf

/** Catalog screen comparing the three integration styles (primary + supplementary). */
@Composable
fun PatternCatalogScreen(innerPadding: PaddingValues) {
    val passwordRules = persistentListOf(required(), minLength(8))
    val primaryField =
        rememberValidatedField(
            initialValue = "",
            rules = persistentListOf(required(), email()),
        )
    val modifierField =
        rememberValidatedField(
            initialValue = "",
            rules = passwordRules,
        )
    val wrapperField =
        rememberValidatedField(
            initialValue = "",
            rules = passwordRules,
        )

    CatalogLazyColumn(innerPadding = innerPadding) {
        item {
            CatalogHero(
                title = "Integration Patterns",
                subtitle = "Primary approach + two supplementary options.",
            )
        }
        item {
            PrimaryPatternSection(field = primaryField)
        }

        item {
            ModifierPatternSection(field = modifierField)
        }

        item {
            WrapperPatternSection(field = wrapperField)
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
