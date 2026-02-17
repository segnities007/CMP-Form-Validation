package com.segnities007.cmp_form_validation.site

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.site.components.CtaSection
import com.segnities007.cmp_form_validation.site.components.Footer
import com.segnities007.cmp_form_validation.site.pages.ApiPage
import com.segnities007.cmp_form_validation.site.pages.DocsPage
import com.segnities007.cmp_form_validation.site.pages.ExamplesPage
import com.segnities007.cmp_form_validation.site.pages.HomePage
import kotlinx.coroutines.launch

@Composable
fun SitePageContent(
    modifier: Modifier = Modifier,
    selectedTab: SiteTab,
    onNavigateToDocs: () -> Unit,
) {
    val scrollStates =
        remember {
            SiteTab.entries.associateWith { ScrollState(initial = 0) }
        }
    val scrollState = scrollStates.getValue(selectedTab)
    val scope = rememberCoroutineScope()
    var containerCoords by remember { mutableStateOf<LayoutCoordinates?>(null) }
    val targetInsetPx = with(LocalDensity.current) { SiteDimens.SidebarScrollTargetInset.roundToPx() }

    val onScrollRequested: (LayoutCoordinates) -> Unit = { sectionCoords ->
        scope.launch {
            val container = containerCoords ?: return@launch
            val posInContainer = container.localPositionOf(sectionCoords, Offset.Zero)
            val target =
                (posInContainer.y.toInt() + scrollState.value - targetInsetPx)
                    .coerceIn(0, scrollState.maxValue)
            scrollState.animateScrollTo(target)
        }
    }

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .onGloballyPositioned { containerCoords = it }
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
    onScrollRequested: (LayoutCoordinates) -> Unit,
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
