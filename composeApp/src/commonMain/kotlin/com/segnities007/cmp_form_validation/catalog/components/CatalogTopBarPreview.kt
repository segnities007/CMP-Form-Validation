package com.segnities007.cmp_form_validation.catalog.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.catalog.CatalogTab

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
