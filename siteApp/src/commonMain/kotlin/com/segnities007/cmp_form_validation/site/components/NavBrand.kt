package com.segnities007.cmp_form_validation.site.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun NavBrand() {
    Text(
        text = "cmp-form-validation",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
    )
}

@Preview
@Composable
private fun NavBrandPreview() {
    SitePreviewTheme {
        NavBrand()
    }
}
