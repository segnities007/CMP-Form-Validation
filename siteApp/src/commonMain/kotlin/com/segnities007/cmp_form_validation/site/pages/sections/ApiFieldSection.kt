package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.runtime.Composable
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.ApiRow
import com.segnities007.cmp_form_validation.site.pages.ApiSection
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.api_field_title
import com.segnities007.cmp_form_validation.site.resources.api_fields_match_desc
import com.segnities007.cmp_form_validation.site.resources.api_form_desc
import com.segnities007.cmp_form_validation.site.resources.api_form_rule_desc
import com.segnities007.cmp_form_validation.site.resources.api_trigger_desc
import com.segnities007.cmp_form_validation.site.resources.api_validated_field_desc
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ApiFieldSection() {
    ApiSection(
        title = stringResource(Res.string.api_field_title),
        rows = {
            ApiRow("ValidatedField<T>", stringResource(Res.string.api_validated_field_desc))
            ApiRow("ValidationTrigger", stringResource(Res.string.api_trigger_desc))
            ApiRow("ValidatedStringForm", stringResource(Res.string.api_form_desc))
            ApiRow("FormRule", stringResource(Res.string.api_form_rule_desc))
            ApiRow("fieldsMatchRule()", stringResource(Res.string.api_fields_match_desc))
        },
    )
}

@Preview
@Composable
private fun ApiFieldSectionPreview() {
    SitePreviewTheme {
        ApiFieldSection()
    }
}
