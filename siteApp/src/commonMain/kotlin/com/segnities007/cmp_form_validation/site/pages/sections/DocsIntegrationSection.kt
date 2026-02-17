package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.InfoCard
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.integration_modifier
import com.segnities007.cmp_form_validation.site.resources.integration_modifier_desc
import com.segnities007.cmp_form_validation.site.resources.integration_primary
import com.segnities007.cmp_form_validation.site.resources.integration_primary_desc
import com.segnities007.cmp_form_validation.site.resources.integration_title
import com.segnities007.cmp_form_validation.site.resources.integration_wrapper
import com.segnities007.cmp_form_validation.site.resources.integration_wrapper_desc
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DocsIntegrationSection() {
    Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.CardItemSpacing)) {
        SectionHeader(stringResource(Res.string.integration_title))
        Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.ApiRowInSectionSpacing)) {
            InfoCard(stringResource(Res.string.integration_primary), stringResource(Res.string.integration_primary_desc))
            InfoCard(stringResource(Res.string.integration_modifier), stringResource(Res.string.integration_modifier_desc))
            InfoCard(stringResource(Res.string.integration_wrapper), stringResource(Res.string.integration_wrapper_desc))
        }
    }
}

@Preview
@Composable
private fun DocsIntegrationSectionPreview() {
    SitePreviewTheme {
        DocsIntegrationSection()
    }
}
