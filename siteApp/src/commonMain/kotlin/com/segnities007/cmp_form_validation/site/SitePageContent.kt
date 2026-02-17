package com.segnities007.cmp_form_validation.site

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.segnities007.cmp_form_validation.site.components.Footer
import com.segnities007.cmp_form_validation.site.pages.ApiPage
import com.segnities007.cmp_form_validation.site.pages.DocsPage
import com.segnities007.cmp_form_validation.site.pages.ExamplesPage
import com.segnities007.cmp_form_validation.site.pages.HomePage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SitePageContent(
    modifier: Modifier = Modifier,
    selectedTab: SiteTab,
    onNavigateToDocs: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        when (selectedTab) {
            SiteTab.Home -> HomePage(onNavigateToDocs = onNavigateToDocs)
            SiteTab.Docs -> DocsPage()
            SiteTab.Api -> ApiPage()
            SiteTab.Examples -> ExamplesPage()
        }
        Footer(onNavigateToDocs = onNavigateToDocs)
    }
}

@Preview
@Composable
private fun SitePageContentPreview() {
    SitePreviewTheme {
        SitePageContent(
            modifier = Modifier,
            selectedTab = SiteTab.Home,
            onNavigateToDocs = {},
        )
    }
}
