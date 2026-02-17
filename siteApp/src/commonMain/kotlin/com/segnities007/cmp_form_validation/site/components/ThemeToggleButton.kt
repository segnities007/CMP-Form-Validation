package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.ThemeMode
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ThemeToggleButton(themeMode: ThemeMode, onClick: () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        shape = CircleShape,
        modifier = Modifier
            .size(36.dp)
            .clickable(onClick = onClick),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = if (themeMode == ThemeMode.DARK) Icons.Rounded.LightMode
                else Icons.Rounded.DarkMode,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Preview
@Composable
private fun ThemeToggleButtonPreview() {
    SitePreviewTheme {
        Row {
            ThemeToggleButton(themeMode = ThemeMode.DARK, onClick = {})
            ThemeToggleButton(themeMode = ThemeMode.LIGHT, onClick = {})
        }
    }
}
