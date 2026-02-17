package com.segnities007.cmp_form_validation.site

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.segnities007.cmp_form_validation.site.components.CtaSection
import com.segnities007.cmp_form_validation.site.components.Footer
import com.segnities007.cmp_form_validation.site.pages.ApiPage
import com.segnities007.cmp_form_validation.site.pages.DocsPage
import com.segnities007.cmp_form_validation.site.pages.ExamplesPage
import com.segnities007.cmp_form_validation.site.pages.HomePage
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SitePageContent(
    modifier: Modifier = Modifier,
    selectedTab: SiteTab,
    onNavigateToDocs: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    val onScrollRequested: (Int) -> Unit = { offset ->
        scope.launch {
            scrollState.animateScrollTo(offset)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
    ) {
        SiteTabContent(
            selectedTab = selectedTab,
            onNavigateToDocs = onNavigateToDocs,
            onScrollRequested = onScrollRequested,
        )
        if (selectedTab.showsBottomCta) {
            CtaSection(onGetStarted = onNavigateToDocs)
        }
        Footer(onNavigateToDocs = onNavigateToDocs)
    }
}

@Composable
private fun SiteTabContent(
    selectedTab: SiteTab,
    onNavigateToDocs: () -> Unit,
    onScrollRequested: (Int) -> Unit,
) {
    when (selectedTab) {
        SiteTab.Home -> HomePage(onNavigateToDocs = onNavigateToDocs)
        SiteTab.Docs -> DocsPage(onScrollRequested = onScrollRequested)
        SiteTab.Api -> ApiPage(onScrollRequested = onScrollRequested)
        SiteTab.Examples -> ExamplesPage(onScrollRequested = onScrollRequested)
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
