package com.segnities007.cmp_form_validation.site.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NoteBox(text: String) {
    CalloutContainer(
        text = text,
        icon = Icons.Rounded.Info,
        tint = MaterialTheme.colorScheme.primary,
    )
}

@Preview
@Composable
private fun NoteBoxPreview() {
    SitePreviewTheme {
        NoteBox("Cross-field validation requires ValidatedStringForm.")
    }
}
