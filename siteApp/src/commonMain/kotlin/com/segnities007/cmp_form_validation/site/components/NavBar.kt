package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.SiteTab
import com.segnities007.cmp_form_validation.site.ThemeMode
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.nav_api
import com.segnities007.cmp_form_validation.site.resources.nav_docs
import com.segnities007.cmp_form_validation.site.resources.nav_examples
import com.segnities007.cmp_form_validation.site.resources.nav_home
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NavBar(
    selectedTab: SiteTab,
    themeMode: ThemeMode,
    localeLabel: String,
    onTabSelected: (SiteTab) -> Unit,
    onThemeToggle: () -> Unit,
    onLocaleToggle: () -> Unit,
) {
    val borderColor = MaterialTheme.colorScheme.surfaceVariant

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .drawBehind {
                drawLine(
                    color = borderColor,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx(),
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .widthIn(max = SiteDimens.MaxContentWidth)
                .fillMaxWidth()
                .height(SiteDimens.NavHeight)
                .padding(horizontal = SiteDimens.ContentPaddingH),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NavBrand()

            Spacer(Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(SiteDimens.NavTabSpacing),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val tabs = listOf(
                    SiteTab.Home to stringResource(Res.string.nav_home),
                    SiteTab.Docs to stringResource(Res.string.nav_docs),
                    SiteTab.Api to stringResource(Res.string.nav_api),
                    SiteTab.Examples to stringResource(Res.string.nav_examples),
                )
                tabs.forEach { (tab, label) ->
                    NavTab(
                        label = label,
                        isActive = tab == selectedTab,
                        onClick = { onTabSelected(tab) },
                    )
                }

                Spacer(Modifier.width(SiteDimens.SectionHeaderGap))
                Box(
                    Modifier
                        .width(1.dp)
                        .height(SiteDimens.SectionHeaderBarHeight)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                )
                Spacer(Modifier.width(SiteDimens.NavActionSpacing))

                LocaleToggleButton(localeLabel = localeLabel, onClick = onLocaleToggle)

                Spacer(Modifier.width(SiteDimens.NavActionSpacing))

                ThemeToggleButton(themeMode = themeMode, onClick = onThemeToggle)
            }
        }
    }
}

@Preview
@Composable
private fun NavBarPreview() {
    SitePreviewTheme {
        NavBar(
            selectedTab = SiteTab.Home,
            themeMode = ThemeMode.DARK,
            localeLabel = "EN",
            onTabSelected = {},
            onThemeToggle = {},
            onLocaleToggle = {},
        )
    }
}
