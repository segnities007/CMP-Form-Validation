package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.CenteredContent
import com.segnities007.cmp_form_validation.site.pages.sections.ApiComposeSection
import com.segnities007.cmp_form_validation.site.pages.sections.ApiCoreSection
import com.segnities007.cmp_form_validation.site.pages.sections.ApiFieldSection
import com.segnities007.cmp_form_validation.site.pages.sections.ApiRulesSection
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.*
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ApiPage() {
    Column {
        Spacer(Modifier.height(SiteDimens.PageTop))
        CenteredContent {
            Text(stringResource(Res.string.api_title), style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Spacer(Modifier.height(SiteDimens.SubSectionContentGap))
            Text(stringResource(Res.string.api_subtitle), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Spacer(Modifier.height(SiteDimens.SubSection))
        CenteredContent {
            Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.ApiSectionSpacing)) {
                ApiCoreSection()
                ApiRulesSection()
                ApiFieldSection()
                ApiComposeSection()
            }
        }
        Spacer(Modifier.height(SiteDimens.PageBottom))
    }
}

@Preview
@Composable
private fun ApiPagePreview() {
    SitePreviewTheme {
        ApiPage()
    }
}
