package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountTree
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.CenteredContent
import com.segnities007.cmp_form_validation.site.components.FeatureCard
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.feature_boundary_desc
import com.segnities007.cmp_form_validation.site.resources.feature_boundary_title
import com.segnities007.cmp_form_validation.site.resources.feature_compose_desc
import com.segnities007.cmp_form_validation.site.resources.feature_compose_title
import com.segnities007.cmp_form_validation.site.resources.feature_stable_desc
import com.segnities007.cmp_form_validation.site.resources.feature_stable_title
import com.segnities007.cmp_form_validation.site.resources.feature_type_safe_desc
import com.segnities007.cmp_form_validation.site.resources.feature_type_safe_title
import com.segnities007.cmp_form_validation.site.resources.why_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeWhySection() {
    CenteredContent {
        SectionHeader(stringResource(Res.string.why_title))
        Spacer(Modifier.height(SiteDimens.SectionContentGap))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(SiteDimens.FlowRowItemSpacing),
            verticalArrangement = Arrangement.spacedBy(SiteDimens.FlowRowItemSpacing),
            modifier = Modifier.fillMaxWidth(),
        ) {
            FeatureCard(
                icon = Icons.Rounded.AccountTree,
                title = stringResource(Res.string.feature_boundary_title),
                description = stringResource(Res.string.feature_boundary_desc),
                modifier = Modifier.weight(1f).widthIn(min = 240.dp),
            )
            FeatureCard(
                icon = Icons.Rounded.Code,
                title = stringResource(Res.string.feature_compose_title),
                description = stringResource(Res.string.feature_compose_desc),
                modifier = Modifier.weight(1f).widthIn(min = 240.dp),
            )
        }
        Spacer(Modifier.height(SiteDimens.FlowRowItemSpacing))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(SiteDimens.FlowRowItemSpacing),
            verticalArrangement = Arrangement.spacedBy(SiteDimens.FlowRowItemSpacing),
            modifier = Modifier.fillMaxWidth(),
        ) {
            FeatureCard(
                icon = Icons.Rounded.Lock,
                title = stringResource(Res.string.feature_stable_title),
                description = stringResource(Res.string.feature_stable_desc),
                modifier = Modifier.weight(1f).widthIn(min = 240.dp),
            )
            FeatureCard(
                icon = Icons.Rounded.Shield,
                title = stringResource(Res.string.feature_type_safe_title),
                description = stringResource(Res.string.feature_type_safe_desc),
                modifier = Modifier.weight(1f).widthIn(min = 240.dp),
            )
        }
    }
}

@Preview
@Composable
private fun HomeWhySectionPreview() {
    SitePreviewTheme {
        HomeWhySection()
    }
}
