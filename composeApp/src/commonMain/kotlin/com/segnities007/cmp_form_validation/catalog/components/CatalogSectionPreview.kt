package com.segnities007.cmp_form_validation.catalog.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun CatalogSectionPreview() {
    MaterialTheme {
        CatalogSection(
            title = "Sample Section",
            description = "Section description",
        ) {
            Text("Section content")
        }
    }
}
