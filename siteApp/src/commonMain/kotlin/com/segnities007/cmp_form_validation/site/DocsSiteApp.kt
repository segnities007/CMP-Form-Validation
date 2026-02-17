package com.segnities007.cmp_form_validation.site

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.segnities007.cmp_form_validation.site.components.Footer
import com.segnities007.cmp_form_validation.site.components.NavBar
import com.segnities007.cmp_form_validation.site.pages.ApiPage
import com.segnities007.cmp_form_validation.site.pages.DocsPage
import com.segnities007.cmp_form_validation.site.pages.ExamplesPage
import com.segnities007.cmp_form_validation.site.pages.HomePage

enum class SiteTab { Home, Docs, Api, Examples }

@Composable
fun DocsSiteApp(
    currentLocaleCode: String = "en",
    onLocaleToggle: () -> Unit = {},
) {
    val systemDark = isSystemInDarkTheme()
    var themeMode by remember { mutableStateOf(if (systemDark) ThemeMode.DARK else ThemeMode.LIGHT) }
    var selectedTab by remember { mutableStateOf(SiteTab.Home) }

    val colorScheme = colorSchemeFor(themeMode)
    val extraColors = extraColorsFor(themeMode)

    CompositionLocalProvider(LocalExtraColors provides extraColors) {
        MaterialTheme(colorScheme = colorScheme) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
            ) {
                NavBar(
                    selectedTab = selectedTab,
                    themeMode = themeMode,
                    localeLabel = if (currentLocaleCode.startsWith("ja")) "JA" else "EN",
                    onTabSelected = { selectedTab = it },
                    onThemeToggle = {
                        themeMode = if (themeMode == ThemeMode.DARK) ThemeMode.LIGHT else ThemeMode.DARK
                    },
                    onLocaleToggle = onLocaleToggle,
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                ) {
                    when (selectedTab) {
                        SiteTab.Home -> HomePage(onNavigateToDocs = { selectedTab = SiteTab.Docs })
                        SiteTab.Docs -> DocsPage()
                        SiteTab.Api -> ApiPage()
                        SiteTab.Examples -> ExamplesPage()
                    }
                    Footer(onNavigateToDocs = { selectedTab = SiteTab.Docs })
                }
            }
        }
    }
}
