package com.segnities007.cmp_form_validation.catalog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.catalog.components.CatalogTopBar
import com.segnities007.cmp_form_validation.catalog.screens.FormCatalogScreen
import com.segnities007.cmp_form_validation.catalog.screens.PatternCatalogScreen
import com.segnities007.cmp_form_validation.catalog.screens.RuleCatalogScreen

enum class CatalogTab(val label: String) {
    Rules("Rules"),
    Patterns("Patterns"),
    Form("Sign-up Form"),
}

/** Top-level catalog shell for validating library behavior in a running app. */
@Composable
fun ValidationCatalogApp() {
    var selectedTab by remember { mutableStateOf(CatalogTab.Rules) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CatalogTopBar(
                selectedTab = selectedTab,
                tabDescription = selectedTab.description,
                onTabSelected = { selectedTab = it },
            )
        },
    ) { innerPadding ->
        when (selectedTab) {
            CatalogTab.Rules -> RuleCatalogScreen(innerPadding)
            CatalogTab.Patterns -> PatternCatalogScreen(innerPadding)
            CatalogTab.Form -> FormCatalogScreen(innerPadding)
        }
    }
}

@Preview
@Composable
private fun ValidationCatalogAppPreview() {
    MaterialTheme {
        ValidationCatalogApp()
    }
}

private val CatalogTab.description: String
    get() = when (this) {
        CatalogTab.Rules -> "Inspect core validation rules independently."
        CatalogTab.Patterns -> "Compare primary and supplementary Compose APIs."
        CatalogTab.Form -> "Validate a complete form flow with cross-field checks."
    }
