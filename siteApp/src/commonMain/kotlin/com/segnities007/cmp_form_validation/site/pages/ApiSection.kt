package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.ApiRow
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ApiSection(
    title: String,
    rows: @Composable () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.CardItemSpacing)) {
        SectionHeader(title)
        Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.ApiRowInSectionSpacing)) {
            rows()
        }
    }
}

@Preview
@Composable
private fun ApiSectionPreview() {
    SitePreviewTheme {
        ApiSection(title = "Core Types") {
            ApiRow("Rule<T>", "Single validation rule", "fun interface Rule<T> { ... }")
            ApiRow("Validator<T>", "Runs rules against a value")
        }
    }
}
