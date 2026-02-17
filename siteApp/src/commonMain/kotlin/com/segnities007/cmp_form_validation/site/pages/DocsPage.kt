package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.CodeBlock
import com.segnities007.cmp_form_validation.site.components.NoteBox
import com.segnities007.cmp_form_validation.site.components.OverviewCard
import com.segnities007.cmp_form_validation.site.components.OverviewMeta
import com.segnities007.cmp_form_validation.site.components.PageHeader
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import com.segnities007.cmp_form_validation.site.components.SidebarLayout
import com.segnities007.cmp_form_validation.site.components.SidebarNavItem
import com.segnities007.cmp_form_validation.site.components.SidebarSectionLabel
import com.segnities007.cmp_form_validation.site.pages.sections.DocsIntegrationSection
import com.segnities007.cmp_form_validation.site.pages.sections.DocsStrategiesSection
import com.segnities007.cmp_form_validation.site.pages.sections.DocsTipsSection
import com.segnities007.cmp_form_validation.site.pages.sections.DocsTriggersSection
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.docs_overview
import com.segnities007.cmp_form_validation.site.resources.docs_subtitle
import com.segnities007.cmp_form_validation.site.resources.docs_time
import com.segnities007.cmp_form_validation.site.resources.docs_title
import com.segnities007.cmp_form_validation.site.resources.integration_title
import com.segnities007.cmp_form_validation.site.resources.overview_beginner
import com.segnities007.cmp_form_validation.site.resources.overview_difficulty
import com.segnities007.cmp_form_validation.site.resources.overview_docs_desc
import com.segnities007.cmp_form_validation.site.resources.overview_reading_time
import com.segnities007.cmp_form_validation.site.resources.overview_sections
import com.segnities007.cmp_form_validation.site.resources.overview_title
import com.segnities007.cmp_form_validation.site.resources.sidebar_advanced
import com.segnities007.cmp_form_validation.site.resources.sidebar_config
import com.segnities007.cmp_form_validation.site.resources.sidebar_guide
import com.segnities007.cmp_form_validation.site.resources.step1_desc
import com.segnities007.cmp_form_validation.site.resources.step1_note
import com.segnities007.cmp_form_validation.site.resources.step1_title
import com.segnities007.cmp_form_validation.site.resources.step2_desc
import com.segnities007.cmp_form_validation.site.resources.step2_explain
import com.segnities007.cmp_form_validation.site.resources.step2_title
import com.segnities007.cmp_form_validation.site.resources.step3_desc
import com.segnities007.cmp_form_validation.site.resources.step3_explain
import com.segnities007.cmp_form_validation.site.resources.step3_title
import com.segnities007.cmp_form_validation.site.resources.step4_title
import com.segnities007.cmp_form_validation.site.resources.step5_title
import com.segnities007.cmp_form_validation.site.resources.step6_desc
import com.segnities007.cmp_form_validation.site.resources.step6_explain
import com.segnities007.cmp_form_validation.site.resources.step6_title
import com.segnities007.cmp_form_validation.site.resources.step7_desc
import com.segnities007.cmp_form_validation.site.resources.step7_explain
import com.segnities007.cmp_form_validation.site.resources.step7_title
import com.segnities007.cmp_form_validation.site.resources.tips_title
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

/**
 * Getting Started documentation page with sidebar navigation and overview card.
 * All sections use a consistent flat layout: SectionHeader → description → code/content.
 */
@Composable
fun DocsPage(onScrollRequested: ((LayoutCoordinates) -> Unit)? = null) {
    var activeSection by remember { mutableIntStateOf(0) }
    val sectionCoords = remember { mutableMapOf<Int, LayoutCoordinates>() }
    val scope = rememberCoroutineScope()

    val sectionLabels =
        listOf(
            stringResource(Res.string.step1_title),
            stringResource(Res.string.step2_title),
            stringResource(Res.string.step3_title),
            stringResource(Res.string.step4_title),
            stringResource(Res.string.step5_title),
            stringResource(Res.string.step6_title),
            stringResource(Res.string.step7_title),
            stringResource(Res.string.integration_title),
            stringResource(Res.string.tips_title),
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
            title = stringResource(Res.string.docs_title),
            subtitle = stringResource(Res.string.docs_subtitle),
        )

        SidebarLayout(
            sidebarContent = {
                SidebarSectionLabel(stringResource(Res.string.sidebar_guide))
                sectionLabels.take(3).forEachIndexed { index, label ->
                    SidebarNavItem(
                        label = label,
                        isActive = activeSection == index,
                        onClick = { handleSectionClick(index) },
                    )
                }
                SidebarSectionLabel(stringResource(Res.string.sidebar_config))
                sectionLabels.subList(3, 5).forEachIndexed { index, label ->
                    SidebarNavItem(
                        label = label,
                        isActive = activeSection == index + 3,
                        onClick = { handleSectionClick(index + 3) },
                    )
                }
                SidebarSectionLabel(stringResource(Res.string.sidebar_advanced))
                sectionLabels.subList(5, sectionLabels.size).forEachIndexed { index, label ->
                    SidebarNavItem(
                        label = label,
                        isActive = activeSection == index + 5,
                        onClick = { handleSectionClick(index + 5) },
                    )
                }
            },
            overviewContent = {
                OverviewCard(
                    title = stringResource(Res.string.overview_title),
                    description = stringResource(Res.string.overview_docs_desc),
                    metaItems =
                        listOf(
                            OverviewMeta(
                                Icons.Rounded.AccessTime,
                                stringResource(Res.string.overview_reading_time),
                                stringResource(Res.string.docs_time),
                            ),
                            OverviewMeta(Icons.Rounded.Layers, stringResource(Res.string.overview_sections), "9"),
                            OverviewMeta(
                                Icons.Rounded.Star,
                                stringResource(Res.string.overview_difficulty),
                                stringResource(Res.string.overview_beginner),
                            ),
                        ),
                )
            },
            mainContent = {
                Spacer(Modifier.height(SiteDimens.SubSection))
                Text(
                    stringResource(Res.string.docs_overview),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 22.sp,
                )

                Spacer(Modifier.height(SiteDimens.Section))

                // All sections use: SectionHeader → desc → code → explanation
                Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.StepSpacing)) {
                    // Step 1: Add Dependencies
                    DocsStepSection(
                        index = 0,
                        sectionCoords = sectionCoords,
                        title = stringResource(Res.string.step1_title),
                        description = stringResource(Res.string.step1_desc),
                        code = CodeSamples.ADD_DEPS,
                        note = stringResource(Res.string.step1_note),
                    )
                    // Step 2: Create a Validated Field
                    DocsStepSection(
                        index = 1,
                        sectionCoords = sectionCoords,
                        title = stringResource(Res.string.step2_title),
                        description = stringResource(Res.string.step2_desc),
                        code = CodeSamples.CREATE_FIELD,
                        explanation = stringResource(Res.string.step2_explain),
                    )
                    // Step 3: Connect to UI
                    DocsStepSection(
                        index = 2,
                        sectionCoords = sectionCoords,
                        title = stringResource(Res.string.step3_title),
                        description = stringResource(Res.string.step3_desc),
                        code = CodeSamples.BIND_UI,
                        explanation = stringResource(Res.string.step3_explain),
                    )
                    // Step 4: Validation Triggers
                    Column(Modifier.onGloballyPositioned { sectionCoords[3] = it }) {
                        DocsTriggersSection()
                    }
                    // Step 5: Validation Strategies
                    Column(Modifier.onGloballyPositioned { sectionCoords[4] = it }) {
                        DocsStrategiesSection()
                    }
                    // Step 6: Build a Multi-Field Form
                    DocsStepSection(
                        index = 5,
                        sectionCoords = sectionCoords,
                        title = stringResource(Res.string.step6_title),
                        description = stringResource(Res.string.step6_desc),
                        code = CodeSamples.FORM,
                        explanation = stringResource(Res.string.step6_explain),
                    )
                    // Step 7: Create Custom Rules
                    DocsStepSection(
                        index = 6,
                        sectionCoords = sectionCoords,
                        title = stringResource(Res.string.step7_title),
                        description = stringResource(Res.string.step7_desc),
                        code = CodeSamples.CUSTOM_RULE,
                        explanation = stringResource(Res.string.step7_explain),
                    )
                    // Integration Styles
                    Column(Modifier.onGloballyPositioned { sectionCoords[7] = it }) {
                        DocsIntegrationSection()
                    }
                    // Tips
                    Column(Modifier.onGloballyPositioned { sectionCoords[8] = it }) {
                        DocsTipsSection()
                    }
                }
                Spacer(Modifier.height(SiteDimens.PageBottom))
            },
        )
    }
}

/**
 * Consistent section layout used for steps in the Docs page.
 * Matches the flat style of DocsTriggersSection / DocsStrategiesSection.
 */
@Composable
private fun DocsStepSection(
    index: Int,
    sectionCoords: MutableMap<Int, LayoutCoordinates>,
    title: String,
    description: String,
    code: String? = null,
    explanation: String? = null,
    note: String? = null,
) {
    Column(
        modifier =
            Modifier.onGloballyPositioned {
                sectionCoords[index] = it
            },
        verticalArrangement = Arrangement.spacedBy(SiteDimens.CardItemSpacing),
    ) {
        SectionHeader(title)
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 22.sp,
        )
        if (code != null) {
            CodeBlock(code, label = "Kotlin")
        }
        if (explanation != null) {
            Text(
                text = explanation,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                lineHeight = 20.sp,
            )
        }
        if (note != null) {
            NoteBox(note)
        }
    }
}

@Preview
@Composable
private fun DocsPagePreview() {
    SitePreviewTheme {
        DocsPage()
    }
}
