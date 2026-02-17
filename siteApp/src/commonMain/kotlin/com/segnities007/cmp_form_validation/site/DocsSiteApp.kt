package com.segnities007.cmp_form_validation.site

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.segnities007.cmp_form_validation.site.components.NavBar
import org.jetbrains.compose.ui.tooling.preview.Preview

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
    val typography = siteTypography()

    CompositionLocalProvider(LocalExtraColors provides extraColors) {
        MaterialTheme(colorScheme = colorScheme, typography = typography) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                topBar = {
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
                },
            ) { innerPadding ->
                SitePageContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    selectedTab = selectedTab,
                    onNavigateToDocs = { selectedTab = SiteTab.Docs },
                )
            }
        }
    }
}

@Preview
@Composable
private fun DocsSiteAppPreview() {
    DocsSiteApp()
}
