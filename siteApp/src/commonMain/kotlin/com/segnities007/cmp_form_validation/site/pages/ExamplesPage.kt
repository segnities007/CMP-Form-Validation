package com.segnities007.cmp_form_validation.site.pages

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
import com.segnities007.cmp_form_validation.site.pages.sections.ExamplesListSection
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.*
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExamplesPage() {
    Column {
        Spacer(Modifier.height(SiteDimens.PageTop))
        CenteredContent {
            Text(stringResource(Res.string.examples_title), style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Spacer(Modifier.height(SiteDimens.SubSectionContentGap))
            Text(stringResource(Res.string.examples_subtitle), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Spacer(Modifier.height(SiteDimens.SubSection))
        CenteredContent {
            ExamplesListSection()
        }
        Spacer(Modifier.height(SiteDimens.PageBottom))
    }
}

@Preview
@Composable
private fun ExamplesPagePreview() {
    SitePreviewTheme {
        ExamplesPage()
    }
}
