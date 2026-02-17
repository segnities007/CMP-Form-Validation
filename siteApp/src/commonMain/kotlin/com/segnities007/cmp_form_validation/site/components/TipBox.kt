package com.segnities007.cmp_form_validation.site.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SitePreviewTheme

@Composable
fun TipBox(text: String) {
    val extra = LocalExtraColors.current
    CalloutContainer(
        text = text,
        icon = Icons.Rounded.Lightbulb,
        tint = extra.tipColor,
    )
}

@Preview
@Composable
private fun TipBoxPreview() {
    SitePreviewTheme {
        TipBox("Use persistentListOf for immutable rule lists.")
    }
}
