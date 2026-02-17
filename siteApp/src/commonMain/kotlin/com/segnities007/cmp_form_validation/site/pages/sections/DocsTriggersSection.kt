package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.CodeBlock
import com.segnities007.cmp_form_validation.site.components.InfoCard
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import com.segnities007.cmp_form_validation.site.pages.CodeSamples
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.step4_desc
import com.segnities007.cmp_form_validation.site.resources.step4_title
import com.segnities007.cmp_form_validation.site.resources.trigger_on_blur_desc
import com.segnities007.cmp_form_validation.site.resources.trigger_on_change_desc
import com.segnities007.cmp_form_validation.site.resources.trigger_on_submit_desc
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DocsTriggersSection() {
    Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.CardItemSpacing)) {
        SectionHeader(stringResource(Res.string.step4_title))
        Text(
            stringResource(Res.string.step4_desc),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.ApiRowInSectionSpacing)) {
            InfoCard("OnChange", stringResource(Res.string.trigger_on_change_desc))
            InfoCard("OnBlur", stringResource(Res.string.trigger_on_blur_desc))
            InfoCard("OnSubmitThenChange", stringResource(Res.string.trigger_on_submit_desc))
        }
        CodeBlock(CodeSamples.TRIGGER, label = "Kotlin")
    }
}

@Preview
@Composable
private fun DocsTriggersSectionPreview() {
    SitePreviewTheme {
        DocsTriggersSection()
    }
}
