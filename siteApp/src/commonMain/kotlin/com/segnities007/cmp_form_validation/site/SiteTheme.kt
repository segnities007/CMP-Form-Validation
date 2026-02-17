package com.segnities007.cmp_form_validation.site

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

enum class ThemeMode { LIGHT, DARK }

// ── Teal palette ─────────────────────────────────────────────────────────────

internal val Teal50 = Color(0xFFE0F2F1)
internal val Teal100 = Color(0xFFB2DFDB)
internal val Teal200 = Color(0xFF80CBC4)
internal val Teal400 = Color(0xFF26A69A)
internal val Teal500 = Color(0xFF009688)
internal val Teal600 = Color(0xFF00897B)
internal val Teal700 = Color(0xFF00796B)
internal val Teal800 = Color(0xFF00695C)
internal val Teal900 = Color(0xFF004D40)

// ── Color schemes ────────────────────────────────────────────────────────────

val DarkSiteColors: ColorScheme = darkColorScheme(
    primary = Teal400,
    onPrimary = Color.White,
    primaryContainer = Teal800,
    onPrimaryContainer = Teal100,
    secondary = Teal200,
    surface = Color(0xFF0F1923),
    surfaceVariant = Color(0xFF162330),
    background = Color(0xFF0B1219),
    onBackground = Color(0xFFE0E6EB),
    onSurface = Color(0xFFE0E6EB),
    onSurfaceVariant = Color(0xFF9EAAB5),
)

val LightSiteColors: ColorScheme = lightColorScheme(
    primary = Teal700,
    onPrimary = Color.White,
    primaryContainer = Teal100,
    onPrimaryContainer = Teal900,
    secondary = Teal600,
    surface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFFF0F4F8),
    background = Color(0xFFF8FAFB),
    onBackground = Color(0xFF1A1C1E),
    onSurface = Color(0xFF1A1C1E),
    onSurfaceVariant = Color(0xFF49545E),
)

// ── Extra colors not covered by MaterialTheme ────────────────────────────────

data class CodeBlockColors(
    val background: Color,
    val text: Color,
    val border: Color,
)

data class HeroColors(
    val gradientStart: Color,
    val gradientEnd: Color,
    val badgeBg: Color,
    val badgeText: Color,
    val subtitle: Color,
    val title: Color,
)

data class SiteExtraColors(
    val codeBlock: CodeBlockColors,
    val hero: HeroColors,
    val footerBg: Color,
    val accentBar: Color,
    val tipColor: Color,
)

val DarkExtraColors = SiteExtraColors(
    codeBlock = CodeBlockColors(
        background = Color(0xFF0D1117),
        text = Color(0xFFE6EDF3),
        border = Teal600,
    ),
    hero = HeroColors(
        gradientStart = Teal900,
        gradientEnd = Color(0xFF0B1219),
        badgeBg = Teal700.copy(alpha = 0.6f),
        badgeText = Teal100,
        subtitle = Teal200,
        title = Color.White,
    ),
    footerBg = Color(0xFF0A0F14),
    accentBar = Teal400,
    tipColor = Color(0xFF4CAF50),
)

val LightExtraColors = SiteExtraColors(
    codeBlock = CodeBlockColors(
        background = Color(0xFFF6F8FA),
        text = Color(0xFF24292F),
        border = Teal500,
    ),
    hero = HeroColors(
        gradientStart = Teal700,
        gradientEnd = Teal50,
        badgeBg = Teal100,
        badgeText = Teal800,
        subtitle = Color.White,
        title = Color.White,
    ),
    footerBg = Color(0xFFE8ECF0),
    accentBar = Teal600,
    tipColor = Color(0xFF4CAF50),
)

val LocalExtraColors = compositionLocalOf { DarkExtraColors }

fun colorSchemeFor(mode: ThemeMode): ColorScheme = when (mode) {
    ThemeMode.LIGHT -> LightSiteColors
    ThemeMode.DARK -> DarkSiteColors
}

fun extraColorsFor(mode: ThemeMode): SiteExtraColors = when (mode) {
    ThemeMode.LIGHT -> LightExtraColors
    ThemeMode.DARK -> DarkExtraColors
}
