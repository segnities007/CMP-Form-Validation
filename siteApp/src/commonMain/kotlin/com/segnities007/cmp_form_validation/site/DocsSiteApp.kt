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

enum class SiteTab(
    val showsBottomCta: Boolean = false,
) {
    Home,
    Docs,
    Api(showsBottomCta = true),
    Examples(showsBottomCta = true),
}

@Composable
fun DocsSiteApp(
    currentLocaleCode: String = "en",
    onLocaleToggle: () -> Unit = {},
) {
    val systemDark = isSystemInDarkTheme()
    var themeMode by remember { mutableStateOf(themeModeForSystem(systemDark)) }
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
                        localeLabel = localeLabelFor(currentLocaleCode),
                        onTabSelected = { selectedTab = it },
                        onThemeToggle = { themeMode = themeMode.toggled() },
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

private fun themeModeForSystem(systemDark: Boolean): ThemeMode =
    if (systemDark) ThemeMode.DARK else ThemeMode.LIGHT

private fun localeLabelFor(currentLocaleCode: String): String =
    if (currentLocaleCode.startsWith("ja")) "JA" else "EN"

private fun ThemeMode.toggled(): ThemeMode =
    if (this == ThemeMode.DARK) ThemeMode.LIGHT else ThemeMode.DARK
