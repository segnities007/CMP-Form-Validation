package com.segnities007.cmp_form_validation.catalog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RuleChips(vararg labels: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(CatalogTokens.ChipSpacing),
    ) {
        labels.forEach { label ->
            CatalogChip(
                label = label,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                textStyle = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Preview
@Composable
private fun RuleChipsPreview() {
    MaterialTheme {
        RuleChips("required", "minLength(3)", "email")
    }
}
