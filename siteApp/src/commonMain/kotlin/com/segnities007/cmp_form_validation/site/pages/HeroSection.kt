package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
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
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun HeroSection(onGetStarted: () -> Unit) {
    val extra = LocalExtraColors.current
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Brush.verticalGradient(listOf(extra.hero.gradientStart, extra.hero.gradientEnd)))
            .padding(vertical = SiteDimens.HeroPaddingV),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = SiteDimens.MaxContentWidth)
                .fillMaxWidth()
                .padding(horizontal = SiteDimens.ContentPaddingH),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SiteDimens.HeroItemSpacing),
        ) {
            Surface(color = extra.hero.badgeBg, shape = RoundedCornerShape(SiteDimens.HeroBadgeCorner)) {
                Text(
                    text = stringResource(Res.string.hero_badge),
                    color = extra.hero.badgeText,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(
                        horizontal = SiteDimens.HeroBadgePaddingH,
                        vertical = SiteDimens.HeroBadgePaddingV,
                    ),
                )
            }
            Text(
                stringResource(Res.string.hero_title),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = extra.hero.title,
                textAlign = TextAlign.Center,
            )
            Text(
                stringResource(Res.string.hero_subtitle),
                style = MaterialTheme.typography.titleLarge,
                color = extra.hero.subtitle,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp,
            )
            Spacer(Modifier.height(SiteDimens.HeroButtonSpacer))
            Row(
                horizontalArrangement = Arrangement.spacedBy(SiteDimens.HeroButtonSpacing, Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(SiteDimens.HeroButtonCorner),
                    modifier = Modifier.clickable(onClick = onGetStarted),
                ) {
                    Text(
                        stringResource(Res.string.hero_get_started),
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
                        stringResource(Res.string.hero_github),
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

@Preview
@Composable
private fun HeroSectionPreview() {
    SitePreviewTheme {
        HeroSection(onGetStarted = {})
    }
}
