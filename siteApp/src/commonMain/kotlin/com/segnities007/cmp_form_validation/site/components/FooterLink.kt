package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun FooterLink(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable(onClick = onClick)
            .padding(4.dp),
    )
}

@Preview
@Composable
private fun FooterLinkPreview() {
    SitePreviewTheme {
        Row {
            FooterLink(text = "GitHub", onClick = {})
            FooterLink(text = "Docs", onClick = {})
        }
    }
}
