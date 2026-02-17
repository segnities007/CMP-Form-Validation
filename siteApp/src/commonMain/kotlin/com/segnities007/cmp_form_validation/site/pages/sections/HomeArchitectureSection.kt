package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.InfoCard
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.architecture_title
import com.segnities007.cmp_form_validation.site.resources.module_compose_desc
import com.segnities007.cmp_form_validation.site.resources.module_core_desc
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeArchitectureSection() {
    SectionHeader(stringResource(Res.string.architecture_title))
    Spacer(Modifier.height(SiteDimens.SectionContentGap))
    Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.ArchitectureCardSpacing)) {
        InfoCard("validation-core", stringResource(Res.string.module_core_desc))
        InfoCard("validation-compose", stringResource(Res.string.module_compose_desc))
    }
}

@Preview
@Composable
private fun HomeArchitectureSectionPreview() {
    SitePreviewTheme {
        HomeArchitectureSection()
    }
}
