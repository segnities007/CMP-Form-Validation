package com.segnities007.cmp_form_validation.site

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SitePreviewTheme(
    themeMode: ThemeMode = ThemeMode.DARK,
    content: @Composable () -> Unit,
) {
    val colorScheme = colorSchemeFor(themeMode)
    val extraColors = extraColorsFor(themeMode)

    CompositionLocalProvider(LocalExtraColors provides extraColors) {
        MaterialTheme(colorScheme = colorScheme) {
            Surface(color = MaterialTheme.colorScheme.background) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun SitePreviewThemePreview() {
    SitePreviewTheme {
        Text("Site preview surface")
    }
}
