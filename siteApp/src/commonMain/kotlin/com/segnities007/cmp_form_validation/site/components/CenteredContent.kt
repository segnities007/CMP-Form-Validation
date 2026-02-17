package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CenteredContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = SiteDimens.MaxContentWidth)
                .fillMaxWidth()
                .padding(horizontal = SiteDimens.ContentPaddingH),
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun CenteredContentPreview() {
    SitePreviewTheme {
        CenteredContent {
            Text("Centered content example")
        }
    }
}
