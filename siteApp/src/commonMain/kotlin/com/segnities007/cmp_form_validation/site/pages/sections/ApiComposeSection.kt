package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.ApiRow
import com.segnities007.cmp_form_validation.site.pages.ApiSection
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.api_compose_field_desc
import com.segnities007.cmp_form_validation.site.resources.api_compose_form_desc
import com.segnities007.cmp_form_validation.site.resources.api_compose_title
import com.segnities007.cmp_form_validation.site.resources.api_modifier_validation_desc
import com.segnities007.cmp_form_validation.site.resources.api_remember_field_desc
import com.segnities007.cmp_form_validation.site.resources.api_remember_form_desc
import com.segnities007.cmp_form_validation.site.resources.api_supporting_text_desc
import com.segnities007.cmp_form_validation.site.resources.api_validated_tf_desc
import org.jetbrains.compose.resources.stringResource

@Composable
fun ApiComposeSection() {
    ApiSection(
        title = stringResource(Res.string.api_compose_title),
        rows = {
            ApiRow("rememberValidatedField", stringResource(Res.string.api_remember_field_desc))
            ApiRow("rememberValidatedStringForm", stringResource(Res.string.api_remember_form_desc))
            ApiRow("ComposeValidatedField<T>", stringResource(Res.string.api_compose_field_desc))
            ApiRow("ComposeValidatedStringForm", stringResource(Res.string.api_compose_form_desc))
            ApiRow("ValidationSupportingText", stringResource(Res.string.api_supporting_text_desc))
            ApiRow("ValidatedOutlinedTextField", stringResource(Res.string.api_validated_tf_desc))
            ApiRow("Modifier.validation()", stringResource(Res.string.api_modifier_validation_desc))
        },
    )
}

@Preview
@Composable
private fun ApiComposeSectionPreview() {
    SitePreviewTheme {
        ApiComposeSection()
    }
}
