package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.SiteUrls
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.hero_badge
import com.segnities007.cmp_form_validation.site.resources.hero_get_started
import com.segnities007.cmp_form_validation.site.resources.hero_github
import com.segnities007.cmp_form_validation.site.resources.hero_subtitle
import com.segnities007.cmp_form_validation.site.resources.hero_title
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun HeroSection(onGetStarted: () -> Unit) {
    val extra = LocalExtraColors.current
    val uriHandler = LocalUriHandler.current

    BoxWithConstraints(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(Brush.verticalGradient(listOf(extra.hero.gradientStart, extra.hero.gradientEnd))),
    ) {
        val compact = maxWidth < 760.dp
        val horizontalPadding = if (compact) SiteDimens.CompactContentPaddingH else SiteDimens.ContentPaddingH
        val verticalPadding = if (compact) SiteDimens.CompactHeroPaddingV else SiteDimens.HeroPaddingV
        val itemSpacing = if (compact) SiteDimens.CompactHeroItemSpacing else SiteDimens.HeroItemSpacing

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier =
                    Modifier
                        .widthIn(max = SiteDimens.MaxContentWidth)
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(itemSpacing),
            ) {
                Surface(color = extra.hero.badgeBg, shape = RoundedCornerShape(SiteDimens.HeroBadgeCorner)) {
                    Text(
                        text = stringResource(Res.string.hero_badge),
                        color = extra.hero.badgeText,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier =
                            Modifier.padding(
                                horizontal = SiteDimens.HeroBadgePaddingH,
                                vertical = SiteDimens.HeroBadgePaddingV,
                            ),
                    )
                }
                Text(
                    stringResource(Res.string.hero_title),
                    style =
                        if (compact) {
                            MaterialTheme.typography.displaySmall
                        } else {
                            MaterialTheme.typography.displayMedium
                        },
                    fontWeight = FontWeight.ExtraBold,
                    color = extra.hero.title,
                    textAlign = TextAlign.Center,
                    lineHeight = if (compact) 44.sp else 60.sp,
                    letterSpacing = (-0.5).sp,
                    modifier = Modifier.widthIn(max = 800.dp),
                )
                Text(
                    stringResource(Res.string.hero_subtitle),
                    style = if (compact) MaterialTheme.typography.titleMedium else MaterialTheme.typography.titleLarge,
                    color = extra.hero.subtitle,
                    textAlign = TextAlign.Center,
                    lineHeight = if (compact) 28.sp else 34.sp,
                    modifier = Modifier.widthIn(max = 680.dp),
                )
                Spacer(Modifier.height(SiteDimens.HeroButtonSpacer))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(SiteDimens.HeroButtonSpacing, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(SiteDimens.HeroButtonSpacing),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(SiteDimens.HeroButtonCorner),
                        shadowElevation = 4.dp,
                        modifier = Modifier.clickable(onClick = onGetStarted),
                    ) {
                        Text(
                            stringResource(Res.string.hero_get_started),
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            modifier =
                                Modifier.padding(
                                    horizontal = SiteDimens.HeroButtonPaddingH,
                                    vertical = SiteDimens.HeroButtonPaddingV,
                                ),
                        )
                    }
                    Surface(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(SiteDimens.HeroButtonCorner),
                        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary),
                        modifier = Modifier.clickable { uriHandler.openUri(SiteUrls.GITHUB) },
                    ) {
                        Text(
                            stringResource(Res.string.hero_github),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            modifier =
                                Modifier.padding(
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
private fun HeroSectionPreview() {
    SitePreviewTheme {
        HeroSection(onGetStarted = {})
    }
}
