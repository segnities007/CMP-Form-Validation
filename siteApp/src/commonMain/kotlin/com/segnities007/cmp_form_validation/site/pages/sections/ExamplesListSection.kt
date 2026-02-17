package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.pages.CodeSamples
import com.segnities007.cmp_form_validation.site.pages.ExampleSection
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.ex_custom_desc
import com.segnities007.cmp_form_validation.site.resources.ex_custom_title
import com.segnities007.cmp_form_validation.site.resources.ex_email_desc
import com.segnities007.cmp_form_validation.site.resources.ex_email_title
import com.segnities007.cmp_form_validation.site.resources.ex_login_desc
import com.segnities007.cmp_form_validation.site.resources.ex_login_title
import com.segnities007.cmp_form_validation.site.resources.ex_modifier_desc
import com.segnities007.cmp_form_validation.site.resources.ex_modifier_title
import com.segnities007.cmp_form_validation.site.resources.ex_signup_desc
import com.segnities007.cmp_form_validation.site.resources.ex_signup_title
import com.segnities007.cmp_form_validation.site.resources.ex_wrapper_desc
import com.segnities007.cmp_form_validation.site.resources.ex_wrapper_title
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExamplesListSection(
    sectionCoords: MutableMap<Int, LayoutCoordinates>? = null,
) {
    Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.ExampleSectionSpacing)) {
        Column(Modifier.onGloballyPositioned { sectionCoords?.set(0, it) }) {
            ExampleSection(stringResource(Res.string.ex_email_title), stringResource(Res.string.ex_email_desc), CodeSamples.EMAIL)
        }
        Column(Modifier.onGloballyPositioned { sectionCoords?.set(1, it) }) {
            ExampleSection(stringResource(Res.string.ex_login_title), stringResource(Res.string.ex_login_desc), CodeSamples.LOGIN)
        }
        Column(Modifier.onGloballyPositioned { sectionCoords?.set(2, it) }) {
            ExampleSection(stringResource(Res.string.ex_signup_title), stringResource(Res.string.ex_signup_desc), CodeSamples.SIGNUP)
        }
        Column(Modifier.onGloballyPositioned { sectionCoords?.set(3, it) }) {
            ExampleSection(stringResource(Res.string.ex_custom_title), stringResource(Res.string.ex_custom_desc), CodeSamples.CUSTOM)
        }
        Column(Modifier.onGloballyPositioned { sectionCoords?.set(4, it) }) {
            ExampleSection(stringResource(Res.string.ex_modifier_title), stringResource(Res.string.ex_modifier_desc), CodeSamples.MODIFIER)
        }
        Column(Modifier.onGloballyPositioned { sectionCoords?.set(5, it) }) {
            ExampleSection(stringResource(Res.string.ex_wrapper_title), stringResource(Res.string.ex_wrapper_desc), CodeSamples.WRAPPER)
        }
    }
}

@Preview
@Composable
private fun ExamplesListSectionPreview() {
    SitePreviewTheme {
        ExamplesListSection()
    }
}
