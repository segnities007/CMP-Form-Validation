package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme

@Composable
fun PageHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    val extra = LocalExtraColors.current

    BoxWithConstraints(
        modifier =
            modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(extra.pageHeaderGradientStart, extra.pageHeaderGradientEnd),
                    ),
                ),
    ) {
        val compact = maxWidth < 760.dp
        val horizontalPadding = if (compact) SiteDimens.CompactContentPaddingH else SiteDimens.ContentPaddingH
        val verticalPadding = if (compact) SiteDimens.CompactPageHeaderPaddingV else SiteDimens.PageHeaderPaddingV

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding),
            contentAlignment = Alignment.TopCenter,
        ) {
            Column(
                modifier =
                    Modifier
                        .widthIn(max = SiteDimens.MaxContentWidth)
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = title,
                    style =
                        if (compact) {
                            MaterialTheme.typography.headlineMedium
                        } else {
                            MaterialTheme.typography.displaySmall
                        },
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    letterSpacing = (-0.3).sp,
                )
                Text(
                    text = subtitle,
                    style =
                        if (compact) {
                            MaterialTheme.typography.bodyLarge
                        } else {
                            MaterialTheme.typography.titleMedium
                        },
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 26.sp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PageHeaderPreview() {
    SitePreviewTheme {
        PageHeader(
            title = "API Reference",
            subtitle = "Complete reference for the validation modules.",
        )
    }
}
