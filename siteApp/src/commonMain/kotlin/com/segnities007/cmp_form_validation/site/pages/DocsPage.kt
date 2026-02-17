package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.CenteredContent
import com.segnities007.cmp_form_validation.site.components.StepCard
import com.segnities007.cmp_form_validation.site.pages.sections.DocsIntegrationSection
import com.segnities007.cmp_form_validation.site.pages.sections.DocsIntroSection
import com.segnities007.cmp_form_validation.site.pages.sections.DocsStrategiesSection
import com.segnities007.cmp_form_validation.site.pages.sections.DocsTipsSection
import com.segnities007.cmp_form_validation.site.pages.sections.DocsTriggersSection
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.*
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DocsPage() {
    Column {
        Spacer(Modifier.height(SiteDimens.PageTop))
        DocsIntroSection()

        Spacer(Modifier.height(SiteDimens.SubSection))
        CenteredContent {
            Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.StepSpacing)) {
                StepCard(stringResource(Res.string.step1_title), stringResource(Res.string.step1_desc), CodeSamples.ADD_DEPS, stringResource(Res.string.step1_note))
                StepCard(stringResource(Res.string.step2_title), stringResource(Res.string.step2_desc), CodeSamples.CREATE_FIELD, stringResource(Res.string.step2_explain))
                StepCard(stringResource(Res.string.step3_title), stringResource(Res.string.step3_desc), CodeSamples.BIND_UI, stringResource(Res.string.step3_explain))
                DocsTriggersSection()
                DocsStrategiesSection()

                StepCard(stringResource(Res.string.step6_title), stringResource(Res.string.step6_desc), CodeSamples.FORM, stringResource(Res.string.step6_explain))
                StepCard(stringResource(Res.string.step7_title), stringResource(Res.string.step7_desc), CodeSamples.CUSTOM_RULE, stringResource(Res.string.step7_explain))
                DocsIntegrationSection()
                DocsTipsSection()
            }
        }
        Spacer(Modifier.height(SiteDimens.PageBottom))
    }
}

@Preview
@Composable
private fun DocsPagePreview() {
    SitePreviewTheme {
        DocsPage()
    }
}
