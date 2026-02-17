package com.segnities007.cmp_form_validation.site.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lightbulb
import androidx.compose.runtime.Composable
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

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
