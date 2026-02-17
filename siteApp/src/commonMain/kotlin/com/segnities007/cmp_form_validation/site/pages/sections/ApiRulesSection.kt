package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.runtime.Composable
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.ApiRow
import com.segnities007.cmp_form_validation.site.pages.ApiSection
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.api_email_desc
import com.segnities007.cmp_form_validation.site.resources.api_max_length_desc
import com.segnities007.cmp_form_validation.site.resources.api_min_length_desc
import com.segnities007.cmp_form_validation.site.resources.api_pattern_desc
import com.segnities007.cmp_form_validation.site.resources.api_required_desc
import com.segnities007.cmp_form_validation.site.resources.api_rules_title
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ApiRulesSection() {
    ApiSection(
        title = stringResource(Res.string.api_rules_title),
        rows = {
            ApiRow("required(trim, message)", stringResource(Res.string.api_required_desc))
            ApiRow("minLength(min, message)", stringResource(Res.string.api_min_length_desc))
            ApiRow("maxLength(max, message)", stringResource(Res.string.api_max_length_desc))
            ApiRow("pattern(regex, code, message)", stringResource(Res.string.api_pattern_desc))
            ApiRow("email(message)", stringResource(Res.string.api_email_desc))
        },
    )
}

@Preview
@Composable
private fun ApiRulesSectionPreview() {
    SitePreviewTheme {
        ApiRulesSection()
    }
}
