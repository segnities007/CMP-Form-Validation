package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme

@Composable
fun CenteredContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val horizontalPadding = if (maxWidth < 760.dp) SiteDimens.CompactContentPaddingH else SiteDimens.ContentPaddingH

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(
                modifier =
                    Modifier
                        .widthIn(max = SiteDimens.MaxContentWidth)
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding),
            ) {
                content()
            }
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
