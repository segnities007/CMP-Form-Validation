package com.segnities007.cmp_form_validation.site

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import com.segnities007.cmp_form_validation.site.components.Footer
import com.segnities007.cmp_form_validation.site.components.NavBar
import com.segnities007.cmp_form_validation.site.pages.ApiPage
import com.segnities007.cmp_form_validation.site.pages.DocsPage
import com.segnities007.cmp_form_validation.site.pages.ExamplesPage
import com.segnities007.cmp_form_validation.site.pages.HomePage
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.noto_sans_jp
import org.jetbrains.compose.resources.Font

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

    val notoSansJp = FontFamily(Font(Res.font.noto_sans_jp))
    val defaultTypography = Typography()
    val typography = Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = notoSansJp),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = notoSansJp),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = notoSansJp),
        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = notoSansJp),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = notoSansJp),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = notoSansJp),
        titleLarge = defaultTypography.titleLarge.copy(fontFamily = notoSansJp),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = notoSansJp),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = notoSansJp),
        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = notoSansJp),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = notoSansJp),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = notoSansJp),
        labelLarge = defaultTypography.labelLarge.copy(fontFamily = notoSansJp),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = notoSansJp),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = notoSansJp),
    )

    CompositionLocalProvider(LocalExtraColors provides extraColors) {
        MaterialTheme(colorScheme = colorScheme, typography = typography) {
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
