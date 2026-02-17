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

data class SiteExtraColors(
    val codeBlockBg: Color,
    val codeBlockText: Color,
    val codeBlockBorder: Color,
    val footerBg: Color,
    val heroGradientStart: Color,
    val heroGradientEnd: Color,
    val heroBadgeBg: Color,
    val heroBadgeText: Color,
    val heroSubtitle: Color,
    val heroTitle: Color,
    val accentBar: Color,
)

val DarkExtraColors = SiteExtraColors(
    codeBlockBg = Color(0xFF0D1117),
    codeBlockText = Color(0xFFE6EDF3),
    codeBlockBorder = Teal600,
    footerBg = Color(0xFF0A0F14),
    heroGradientStart = Teal900,
    heroGradientEnd = Color(0xFF0B1219),
    heroBadgeBg = Teal700.copy(alpha = 0.6f),
    heroBadgeText = Teal100,
    heroSubtitle = Teal200,
    heroTitle = Color.White,
    accentBar = Teal400,
)

val LightExtraColors = SiteExtraColors(
    codeBlockBg = Color(0xFFF6F8FA),
    codeBlockText = Color(0xFF24292F),
    codeBlockBorder = Teal500,
    footerBg = Color(0xFFE8ECF0),
    heroGradientStart = Teal700,
    heroGradientEnd = Teal50,
    heroBadgeBg = Teal100,
    heroBadgeText = Teal800,
    heroSubtitle = Color.White,
    heroTitle = Color.White,
    accentBar = Teal600,
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
