package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Api
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.OverviewCard
import com.segnities007.cmp_form_validation.site.components.OverviewMeta
import com.segnities007.cmp_form_validation.site.components.PageHeader
import com.segnities007.cmp_form_validation.site.components.SidebarLayout
import com.segnities007.cmp_form_validation.site.components.SidebarNavItem
import com.segnities007.cmp_form_validation.site.components.SidebarSectionLabel
import com.segnities007.cmp_form_validation.site.pages.sections.ApiComposeSection
import com.segnities007.cmp_form_validation.site.pages.sections.ApiCoreSection
import com.segnities007.cmp_form_validation.site.pages.sections.ApiFieldSection
import com.segnities007.cmp_form_validation.site.pages.sections.ApiRulesSection
import com.segnities007.cmp_form_validation.site.resources.Res
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
fun ApiPage(onScrollRequested: ((LayoutCoordinates) -> Unit)? = null) {
    var activeSection by remember { mutableIntStateOf(0) }
    val sectionCoords = remember { mutableMapOf<Int, LayoutCoordinates>() }
    val scope = rememberCoroutineScope()

    val sectionLabels =
        listOf(
            stringResource(Res.string.api_core_title),
            stringResource(Res.string.api_rules_title),
            stringResource(Res.string.api_field_title),
            stringResource(Res.string.api_compose_title),
        )

    fun handleSectionClick(index: Int) {
        activeSection = index
        val coords = sectionCoords[index]
        if (coords != null && onScrollRequested != null) {
            scope.launch { onScrollRequested(coords) }
        }
    }

    Column {
        PageHeader(
            title = stringResource(Res.string.api_title),
            subtitle = stringResource(Res.string.api_subtitle),
        )

        SidebarLayout(
            sidebarContent = {
                SidebarSectionLabel(stringResource(Res.string.sidebar_core_types))
                SidebarNavItem(
                    label = sectionLabels[0],
                    isActive = activeSection == 0,
                    onClick = { handleSectionClick(0) },
                )
                SidebarSectionLabel(stringResource(Res.string.sidebar_rules))
                SidebarNavItem(
                    label = sectionLabels[1],
                    isActive = activeSection == 1,
                    onClick = { handleSectionClick(1) },
                )
                SidebarSectionLabel(stringResource(Res.string.sidebar_field_form))
                SidebarNavItem(
                    label = sectionLabels[2],
                    isActive = activeSection == 2,
                    onClick = { handleSectionClick(2) },
                )
                SidebarSectionLabel(stringResource(Res.string.sidebar_compose_api))
                SidebarNavItem(
                    label = sectionLabels[3],
                    isActive = activeSection == 3,
                    onClick = { handleSectionClick(3) },
                )
            },
            overviewContent = {
                OverviewCard(
                    title = stringResource(Res.string.overview_title),
                    description = stringResource(Res.string.overview_api_desc),
                    metaItems =
                        listOf(
                            OverviewMeta(
                                Icons.Rounded.Layers,
                                stringResource(Res.string.overview_modules),
                                stringResource(Res.string.overview_two_modules),
                            ),
                            OverviewMeta(
                                Icons.Rounded.Api,
                                stringResource(Res.string.overview_sections),
                                stringResource(Res.string.overview_api_types),
                            ),
                        ),
                )
            },
            mainContent = {
                Spacer(Modifier.height(SiteDimens.SubSection))
                Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.ApiSectionSpacing)) {
                    Column(Modifier.onGloballyPositioned { sectionCoords[0] = it }) {
                        ApiCoreSection()
                    }
                    Column(Modifier.onGloballyPositioned { sectionCoords[1] = it }) {
                        ApiRulesSection()
                    }
                    Column(Modifier.onGloballyPositioned { sectionCoords[2] = it }) {
                        ApiFieldSection()
                    }
                    Column(Modifier.onGloballyPositioned { sectionCoords[3] = it }) {
                        ApiComposeSection()
                    }
                }
                Spacer(Modifier.height(SiteDimens.PageBottom))
            },
        )
    }
}

@Preview
@Composable
private fun ApiPagePreview() {
    SitePreviewTheme {
        ApiPage()
    }
}
