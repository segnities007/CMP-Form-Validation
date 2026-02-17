package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.runtime.Composable
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.ApiRow
import com.segnities007.cmp_form_validation.site.pages.ApiSection
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.api_core_title
import com.segnities007.cmp_form_validation.site.resources.api_error_code_desc
import com.segnities007.cmp_form_validation.site.resources.api_error_desc
import com.segnities007.cmp_form_validation.site.resources.api_result_desc
import com.segnities007.cmp_form_validation.site.resources.api_rule_desc
import com.segnities007.cmp_form_validation.site.resources.api_strategy_desc
import com.segnities007.cmp_form_validation.site.resources.api_validator_desc
import com.segnities007.cmp_form_validation.site.resources.api_validator_of_desc
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ApiCoreSection() {
    ApiSection(
        title = stringResource(Res.string.api_core_title),
        rows = {
            ApiRow("Rule<T>", stringResource(Res.string.api_rule_desc), "fun interface Rule<T> { fun validate(value: T): ValidationError? }")
            ApiRow("Validator<T>", stringResource(Res.string.api_validator_desc), "class Validator<T>(rules: List<Rule<T>>, strategy: ValidationStrategy)")
            ApiRow("ValidationResult", stringResource(Res.string.api_result_desc), "data class ValidationResult(errors: ImmutableList<ValidationError>)")
            ApiRow("ValidationError", stringResource(Res.string.api_error_desc), "data class ValidationError(code: String, defaultMessage: String, path: String?, meta: ImmutableMap<String, String>)")
            ApiRow("ValidationStrategy", stringResource(Res.string.api_strategy_desc))
            ApiRow("ErrorCode", stringResource(Res.string.api_error_code_desc))
            ApiRow("validatorOf()", stringResource(Res.string.api_validator_of_desc))
        },
    )
}

@Preview
@Composable
private fun ApiCoreSectionPreview() {
    SitePreviewTheme {
        ApiCoreSection()
    }
}
