package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.SiteUrls
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.footer_copyright
import com.segnities007.cmp_form_validation.site.resources.footer_license
import com.segnities007.cmp_form_validation.site.resources.footer_tagline
import com.segnities007.cmp_form_validation.site.resources.hero_github
import com.segnities007.cmp_form_validation.site.resources.nav_docs
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Footer(onNavigateToDocs: () -> Unit) {
    val extra = LocalExtraColors.current
    val uriHandler = LocalUriHandler.current

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    listOf(extra.ctaGradientEnd, extra.footerBg),
                ),
            ),
        contentAlignment = Alignment.Center,
    ) {
        val compact = maxWidth < 760.dp
        val horizontalPadding = if (compact) SiteDimens.CompactContentPaddingH else SiteDimens.ContentPaddingH

        Column(
            modifier = Modifier
                .widthIn(max = SiteDimens.MaxContentWidth)
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding)
                .padding(vertical = SiteDimens.FooterVerticalPadding),
        ) {
            // Top divider
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
            )
            Spacer(Modifier.height(SiteDimens.FooterVerticalPadding))

            if (!compact) {
                // Desktop: horizontal layout
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = "cmpformvalidation",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = stringResource(Res.string.footer_tagline),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 20.sp,
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.End,
                    ) {
                        FooterLink(
                            text = stringResource(Res.string.hero_github),
                            onClick = { uriHandler.openUri(SiteUrls.GITHUB) },
                        )
                        FooterLink(
                            text = stringResource(Res.string.nav_docs),
                            onClick = onNavigateToDocs,
                        )
                        FooterLink(
                            text = stringResource(Res.string.footer_license),
                            onClick = { uriHandler.openUri(SiteUrls.LICENSE) },
                        )
                    }
                }
            } else {
                // Mobile: stacked layout
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(SiteDimens.FooterItemSpacing),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "cmpformvalidation",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = stringResource(Res.string.footer_tagline),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp,
                    )
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(SiteDimens.FooterLinkSpacing),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        FooterLink(
                            text = stringResource(Res.string.hero_github),
                            onClick = { uriHandler.openUri(SiteUrls.GITHUB) },
                        )
                        FooterLink(
                            text = stringResource(Res.string.nav_docs),
                            onClick = onNavigateToDocs,
                        )
                        FooterLink(
                            text = stringResource(Res.string.footer_license),
                            onClick = { uriHandler.openUri(SiteUrls.LICENSE) },
                        )
                    }
                }
            }

            Spacer(Modifier.height(SiteDimens.FooterBottomSpacer))
            Text(
                text = stringResource(Res.string.footer_copyright),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
private fun FooterPreview() {
    SitePreviewTheme {
        Footer(onNavigateToDocs = {})
    }
}
