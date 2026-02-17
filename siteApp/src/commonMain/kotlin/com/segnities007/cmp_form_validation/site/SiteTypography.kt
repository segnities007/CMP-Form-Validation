package com.segnities007.cmp_form_validation.site

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.noto_sans_jp
import org.jetbrains.compose.resources.Font
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun siteTypography(): Typography {
    val fontFamily = FontFamily(Font(Res.font.noto_sans_jp))
    return Typography().withFontFamily(fontFamily)
}

private fun Typography.withFontFamily(fontFamily: FontFamily): Typography = Typography(
    displayLarge = displayLarge.withFontFamily(fontFamily),
    displayMedium = displayMedium.withFontFamily(fontFamily),
    displaySmall = displaySmall.withFontFamily(fontFamily),
    headlineLarge = headlineLarge.withFontFamily(fontFamily),
    headlineMedium = headlineMedium.withFontFamily(fontFamily),
    headlineSmall = headlineSmall.withFontFamily(fontFamily),
    titleLarge = titleLarge.withFontFamily(fontFamily),
    titleMedium = titleMedium.withFontFamily(fontFamily),
    titleSmall = titleSmall.withFontFamily(fontFamily),
    bodyLarge = bodyLarge.withFontFamily(fontFamily),
    bodyMedium = bodyMedium.withFontFamily(fontFamily),
    bodySmall = bodySmall.withFontFamily(fontFamily),
    labelLarge = labelLarge.withFontFamily(fontFamily),
    labelMedium = labelMedium.withFontFamily(fontFamily),
    labelSmall = labelSmall.withFontFamily(fontFamily),
)

private fun TextStyle.withFontFamily(fontFamily: FontFamily): TextStyle = copy(fontFamily = fontFamily)

@Preview
@Composable
private fun SiteTypographyPreview() {
    MaterialTheme(typography = siteTypography()) {
        Text("Typography Preview")
    }
}
