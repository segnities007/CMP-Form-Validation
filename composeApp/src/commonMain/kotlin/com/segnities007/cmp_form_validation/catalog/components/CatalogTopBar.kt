package com.segnities007.cmp_form_validation.catalog.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.catalog.CatalogTab

@Composable
fun CatalogTopBar(
    selectedTab: CatalogTab,
    tabDescription: String,
    onTabSelected: (CatalogTab) -> Unit,
) {
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
                    onClick = { onTabSelected(tab) },
                    text = { Text(tab.label) },
                )
            }
        }
    }
}

@Preview
@Composable
private fun CatalogTopBarPreview() {
    MaterialTheme {
        CatalogTopBar(
            selectedTab = CatalogTab.Rules,
            tabDescription = "Inspect core validation rules independently.",
            onTabSelected = {},
        )
    }
}
