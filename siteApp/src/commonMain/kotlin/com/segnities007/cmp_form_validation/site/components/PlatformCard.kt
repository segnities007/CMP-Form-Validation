package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import androidx.compose.ui.tooling.preview.Preview

/**
 * Card showing a supported platform (Android, iOS, Desktop, Web).
 * Use with `Modifier.weight(1f)` in a FlowRow for equal widths.
 * Uses `fillMaxWidth` to stretch to match siblings within a weighted row.
 */
@Composable
fun PlatformCard(
    icon: ImageVector,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    val extra = LocalExtraColors.current

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
        shape = RoundedCornerShape(SiteDimens.CardCorner),
        border = BorderStroke(SiteDimens.CardBorderWidth, extra.cardBorder),
    ) {
        Column(
            modifier = Modifier.padding(SiteDimens.CardPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SiteDimens.PlatformCardItemSpacing),
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                shape = CircleShape,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(SiteDimens.PlatformIconPadding)
                        .size(SiteDimens.PlatformIconSize),
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
            )
        }
    }
}

@Preview
@Composable
private fun PlatformCardPreview() {
    SitePreviewTheme {
        PlatformCard(
            icon = Icons.Rounded.PhoneAndroid,
            title = "Android",
            description = "Full Android support",
        )
    }
}
