package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.CodeBlock
import com.segnities007.cmp_form_validation.site.components.InfoCard
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import com.segnities007.cmp_form_validation.site.pages.CodeSamples
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.step5_desc
import com.segnities007.cmp_form_validation.site.resources.step5_title
import com.segnities007.cmp_form_validation.site.resources.strategy_collect_all_desc
import com.segnities007.cmp_form_validation.site.resources.strategy_first_error_desc
import org.jetbrains.compose.resources.stringResource

@Composable
fun DocsStrategiesSection() {
    Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.CardItemSpacing)) {
        SectionHeader(stringResource(Res.string.step5_title))
        Text(
            stringResource(Res.string.step5_desc),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.ApiRowInSectionSpacing)) {
            InfoCard("CollectAll", stringResource(Res.string.strategy_collect_all_desc))
            InfoCard("FirstError", stringResource(Res.string.strategy_first_error_desc))
        }
        CodeBlock(CodeSamples.STRATEGY, label = "Kotlin")
    }
}

@Preview
@Composable
private fun DocsStrategiesSectionPreview() {
    SitePreviewTheme {
        DocsStrategiesSection()
    }
}
