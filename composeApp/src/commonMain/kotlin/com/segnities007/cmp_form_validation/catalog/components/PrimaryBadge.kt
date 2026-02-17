package com.segnities007.cmp_form_validation.catalog.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryBadge(label: String) {
    Surface(
        modifier = Modifier.wrapContentWidth(),
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.primary,
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Preview
@Composable
private fun PrimaryBadgePreview() {
    MaterialTheme {
        PrimaryBadge("Primary")
    }
}
