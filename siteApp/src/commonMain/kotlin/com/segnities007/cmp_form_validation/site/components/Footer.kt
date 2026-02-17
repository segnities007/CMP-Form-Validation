package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
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

@Composable
fun Footer(onNavigateToDocs: () -> Unit) {
    val extra = LocalExtraColors.current
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(extra.footerBg)
            .padding(vertical = SiteDimens.FooterVerticalPadding),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = SiteDimens.MaxContentWidth)
                .fillMaxWidth()
                .padding(horizontal = SiteDimens.ContentPaddingH),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SiteDimens.FooterItemSpacing),
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
            )
            Row(horizontalArrangement = Arrangement.spacedBy(SiteDimens.FooterLinkSpacing)) {
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
            Spacer(Modifier.height(SiteDimens.FooterBottomSpacer))
            Text(
                text = stringResource(Res.string.footer_copyright),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
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
