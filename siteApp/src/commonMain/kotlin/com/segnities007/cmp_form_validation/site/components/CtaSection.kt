package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.segnities007.cmp_form_validation.site.resources.cta_github
import com.segnities007.cmp_form_validation.site.resources.cta_start
import com.segnities007.cmp_form_validation.site.resources.cta_subtitle
import com.segnities007.cmp_form_validation.site.resources.cta_title
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CtaSection(
    onGetStarted: () -> Unit,
) {
    val extra = LocalExtraColors.current
    val uriHandler = LocalUriHandler.current

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    listOf(extra.ctaGradientStart, extra.ctaGradientEnd),
                ),
            ),
    ) {
        val compact = maxWidth < 760.dp
        val horizontalPadding = if (compact) SiteDimens.CompactContentPaddingH else SiteDimens.ContentPaddingH
        val verticalPadding = if (compact) SiteDimens.CompactCtaPaddingV else SiteDimens.CtaPaddingV

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = verticalPadding),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = SiteDimens.MaxContentWidth)
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = stringResource(Res.string.cta_title),
                    style = if (compact) MaterialTheme.typography.headlineSmall
                    else MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = stringResource(Res.string.cta_subtitle),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp,
                    modifier = Modifier.widthIn(max = 560.dp),
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(SiteDimens.HeroButtonSpacing, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(SiteDimens.HeroButtonSpacing),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(SiteDimens.HeroButtonCorner),
                        shadowElevation = 2.dp,
                        modifier = Modifier.clickable(onClick = onGetStarted),
                    ) {
                        Text(
                            stringResource(Res.string.cta_start),
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(
                                horizontal = SiteDimens.HeroButtonPaddingH,
                                vertical = SiteDimens.HeroButtonPaddingV,
                            ),
                        )
                    }
                    Surface(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(SiteDimens.HeroButtonCorner),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        modifier = Modifier.clickable { uriHandler.openUri(SiteUrls.GITHUB) },
                    ) {
                        Text(
                            stringResource(Res.string.cta_github),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(
                                horizontal = SiteDimens.HeroButtonPaddingH,
                                vertical = SiteDimens.HeroButtonPaddingV,
                            ),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CtaSectionPreview() {
    SitePreviewTheme {
        CtaSection(onGetStarted = {})
    }
}
