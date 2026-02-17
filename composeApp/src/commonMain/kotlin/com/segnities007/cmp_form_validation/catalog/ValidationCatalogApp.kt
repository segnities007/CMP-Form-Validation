package com.segnities007.cmp_form_validation.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.catalog.screens.FormCatalogScreen
import com.segnities007.cmp_form_validation.catalog.screens.PatternCatalogScreen
import com.segnities007.cmp_form_validation.catalog.screens.RuleCatalogScreen

private enum class CatalogTab(val label: String) {
    Rules("Rules"),
    Patterns("Patterns"),
    Form("Sign-up Form"),
}

/** Top-level catalog shell for validating library behavior in a running app. */
@Composable
fun ValidationCatalogApp() {
    var selectedTab by remember { mutableStateOf(CatalogTab.Rules) }
    val tabDescription = when (selectedTab) {
        CatalogTab.Rules -> "Inspect core validation rules independently."
        CatalogTab.Patterns -> "Compare primary and supplementary Compose APIs."
        CatalogTab.Form -> "Validate a complete form flow with cross-field checks."
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
            ) {
                Text(
                    text = "cmpformvalidation catalog",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
                Text(
                    text = tabDescription,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                )
                PrimaryTabRow(selectedTabIndex = selectedTab.ordinal) {
                    CatalogTab.entries.forEach { tab ->
                        Tab(
                            selected = selectedTab == tab,
                            onClick = { selectedTab = tab },
                            text = { Text(tab.label) },
                        )
                    }
                }
            }
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
