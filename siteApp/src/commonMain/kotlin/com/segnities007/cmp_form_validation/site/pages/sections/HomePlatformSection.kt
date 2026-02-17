package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Computer
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material.icons.rounded.PhoneIphone
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.PlatformCard
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.platform_android
import com.segnities007.cmp_form_validation.site.resources.platform_android_desc
import com.segnities007.cmp_form_validation.site.resources.platform_desktop
import com.segnities007.cmp_form_validation.site.resources.platform_desktop_desc
import com.segnities007.cmp_form_validation.site.resources.platform_ios
import com.segnities007.cmp_form_validation.site.resources.platform_ios_desc
import com.segnities007.cmp_form_validation.site.resources.platform_subtitle
import com.segnities007.cmp_form_validation.site.resources.platform_title
import com.segnities007.cmp_form_validation.site.resources.platform_web
import com.segnities007.cmp_form_validation.site.resources.platform_web_desc
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomePlatformSection() {
    SectionHeader(stringResource(Res.string.platform_title))
    Spacer(Modifier.height(SiteDimens.SubSectionContentGap))
    Text(
        text = stringResource(Res.string.platform_subtitle),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        lineHeight = 24.sp,
    )
    Spacer(Modifier.height(SiteDimens.SectionContentGap))
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(SiteDimens.FlowRowItemSpacing),
        verticalArrangement = Arrangement.spacedBy(SiteDimens.FlowRowItemSpacing),
        modifier = Modifier.fillMaxWidth(),
    ) {
        PlatformCard(Icons.Rounded.PhoneAndroid, stringResource(Res.string.platform_android), stringResource(Res.string.platform_android_desc), Modifier.weight(1f).widthIn(min = 180.dp))
        PlatformCard(Icons.Rounded.PhoneIphone, stringResource(Res.string.platform_ios), stringResource(Res.string.platform_ios_desc), Modifier.weight(1f).widthIn(min = 180.dp))
        PlatformCard(Icons.Rounded.Computer, stringResource(Res.string.platform_desktop), stringResource(Res.string.platform_desktop_desc), Modifier.weight(1f).widthIn(min = 180.dp))
        PlatformCard(Icons.Rounded.Language, stringResource(Res.string.platform_web), stringResource(Res.string.platform_web_desc), Modifier.weight(1f).widthIn(min = 180.dp))
    }
}

@Preview
@Composable
private fun HomePlatformSectionPreview() {
    SitePreviewTheme {
        HomePlatformSection()
    }
}
