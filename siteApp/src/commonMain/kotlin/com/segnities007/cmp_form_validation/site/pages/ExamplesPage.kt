package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.OverviewCard
import com.segnities007.cmp_form_validation.site.components.OverviewMeta
import com.segnities007.cmp_form_validation.site.components.PageHeader
import com.segnities007.cmp_form_validation.site.components.SidebarLayout
import com.segnities007.cmp_form_validation.site.components.SidebarNavItem
import com.segnities007.cmp_form_validation.site.components.SidebarSectionLabel
import com.segnities007.cmp_form_validation.site.pages.sections.ExamplesListSection
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.*
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExamplesPage(onScrollRequested: ((Int) -> Unit)? = null) {
    var activeSection by remember { mutableIntStateOf(0) }

    val exampleLabels = listOf(
        stringResource(Res.string.ex_email_title),
        stringResource(Res.string.ex_login_title),
        stringResource(Res.string.ex_signup_title),
        stringResource(Res.string.ex_custom_title),
        stringResource(Res.string.ex_modifier_title),
        stringResource(Res.string.ex_wrapper_title),
    )

    Column {
        PageHeader(
            title = stringResource(Res.string.examples_title),
            subtitle = stringResource(Res.string.examples_subtitle),
        )

        SidebarLayout(
            sidebarContent = {
                SidebarSectionLabel(stringResource(Res.string.sidebar_examples))
                exampleLabels.forEachIndexed { index, label ->
                    SidebarNavItem(
                        label = label,
                        isActive = activeSection == index,
                        onClick = { activeSection = index },
                    )
                }
            },
            overviewContent = {
                OverviewCard(
                    title = stringResource(Res.string.overview_title),
                    description = stringResource(Res.string.overview_examples_desc),
                    metaItems = listOf(
                        OverviewMeta(Icons.Rounded.Code, stringResource(Res.string.overview_count), "6"),
                        OverviewMeta(Icons.Rounded.Star, stringResource(Res.string.overview_difficulty), stringResource(Res.string.overview_beginner)),
                        OverviewMeta(Icons.Rounded.Layers, stringResource(Res.string.overview_modules), stringResource(Res.string.overview_two_modules)),
                    ),
                )
            },
            mainContent = {
                Spacer(Modifier.height(SiteDimens.SubSection))
                ExamplesListSection()
                Spacer(Modifier.height(SiteDimens.PageBottom))
            },
        )
    }
}

@Preview
@Composable
private fun ExamplesPagePreview() {
    SitePreviewTheme {
        ExamplesPage()
    }
}
