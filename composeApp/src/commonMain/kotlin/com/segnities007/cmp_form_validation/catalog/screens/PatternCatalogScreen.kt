package com.segnities007.cmp_form_validation.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.catalog.components.CatalogHero
import com.segnities007.cmp_form_validation.catalog.screens.components.ModifierPatternSection
import com.segnities007.cmp_form_validation.catalog.screens.components.PrimaryPatternSection
import com.segnities007.cmp_form_validation.catalog.screens.components.WrapperPatternSection
import com.segnities007.cmp_form_validation.validation.email
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.required
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
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
