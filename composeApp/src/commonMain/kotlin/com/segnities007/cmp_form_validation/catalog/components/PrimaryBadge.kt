package com.segnities007.cmp_form_validation.catalog.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryBadge(label: String) {
    CatalogChip(
        label = label,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        textStyle = MaterialTheme.typography.labelMedium,
    )
}

@Preview
@Composable
private fun PrimaryBadgePreview() {
    MaterialTheme {
        PrimaryBadge("Primary")
    }
}
