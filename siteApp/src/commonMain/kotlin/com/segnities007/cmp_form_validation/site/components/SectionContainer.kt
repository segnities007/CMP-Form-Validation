package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SectionContainer(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor),
    ) {
        val compact = maxWidth < 760.dp
        val horizontalPadding = if (compact) SiteDimens.CompactContentPaddingH else SiteDimens.ContentPaddingH
        val verticalPadding = if (compact) SiteDimens.CompactSectionPaddingV else SiteDimens.SectionPaddingV

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = verticalPadding),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(
                modifier = Modifier
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
private fun SectionContainerPreview() {
    SitePreviewTheme {
        SectionContainer {
            Text("Section content")
        }
    }
}
