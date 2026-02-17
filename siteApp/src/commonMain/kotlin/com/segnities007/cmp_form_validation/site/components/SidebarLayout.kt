package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme

/**
 * Three-column layout: sidebar (left) + main content (center) + optional overview card (right).
 * Collapses to single column below [SiteDimens.SidebarBreakpoint].
 *
 * The main content column uses `weight(1f)` to fill all remaining horizontal space
 * after the fixed-width sidebar and overview card.
 */
@Composable
fun SidebarLayout(
    modifier: Modifier = Modifier,
    sidebarContent: @Composable () -> Unit,
    overviewContent: (@Composable () -> Unit)? = null,
    mainContent: @Composable () -> Unit,
) {
    val borderColor = LocalExtraColors.current.overviewCardBorder

    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val isExpanded = maxWidth >= SiteDimens.SidebarBreakpoint

        if (isExpanded) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = SiteDimens.ContentPaddingH),
            ) {
                // Sidebar — fixed width
                Column(
                    modifier =
                        Modifier
                            .width(SiteDimens.SidebarWidth)
                            .padding(top = SiteDimens.SidebarPaddingV)
                            .drawBehind {
                                drawLine(
                                    color = borderColor,
                                    start = Offset(size.width, 0f),
                                    end = Offset(size.width, size.height),
                                    strokeWidth = 1.dp.toPx(),
                                )
                            }
                            .padding(end = 16.dp),
                ) {
                    sidebarContent()
                }

                // Main content — fills all remaining horizontal space
                Column(
                    modifier =
                        Modifier
                            .weight(1f)
                            .padding(horizontal = 24.dp),
                ) {
                    mainContent()
                }

                // Overview card — fixed width (optional)
                if (overviewContent != null) {
                    Column(
                        modifier =
                            Modifier
                                .width(SiteDimens.OverviewCardWidth)
                                .padding(top = SiteDimens.SidebarPaddingV),
                    ) {
                        overviewContent()
                    }
                }
            }
        } else {
            CenteredContent {
                mainContent()
            }
        }
    }
}

@Preview
@Composable
private fun SidebarLayoutPreview() {
    SitePreviewTheme {
        SidebarLayout(
            sidebarContent = {
                SidebarSectionLabel("GUIDE")
                SidebarNavItem("Introduction", isActive = true, onClick = {})
                SidebarNavItem("Setup", isActive = false, onClick = {})
            },
            overviewContent = {
                Text("Overview card placeholder")
            },
            mainContent = {
                Text("Main content area", style = MaterialTheme.typography.headlineMedium)
            },
        )
    }
}
