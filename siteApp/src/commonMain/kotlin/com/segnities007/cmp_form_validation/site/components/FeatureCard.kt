package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FeatureCard(
    icon: ImageVector,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    val extra = LocalExtraColors.current
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        shape = RoundedCornerShape(SiteDimens.CardCorner),
    ) {
        Column(
            modifier = Modifier.padding(SiteDimens.CardPadding),
            verticalArrangement = Arrangement.spacedBy(SiteDimens.CardItemSpacing),
        ) {
            Surface(
                color = extra.accentBar.copy(alpha = 0.15f),
                shape = RoundedCornerShape(SiteDimens.FeatureIconBgCorner),
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = extra.accentBar,
                    modifier = Modifier
                        .padding(SiteDimens.FeatureIconPadding)
                        .size(SiteDimens.FeatureIconSize),
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 22.sp,
            )
        }
    }
}

@Preview
@Composable
private fun FeatureCardPreview() {
    SitePreviewTheme {
        FeatureCard(
            icon = Icons.Rounded.Code,
            title = "Feature Title",
            description = "Feature description text goes here.",
        )
    }
}
