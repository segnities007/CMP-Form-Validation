package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme

/**
 * Feature highlight card used in "Why CMP Form Validation?" section.
 *
 * Previously wrapped in BoxWithConstraints, which broke the FlowRow weight()
 * modifier — the weight was applied to the inner Surface instead of the
 * BoxWithConstraints, causing the Surface to not fill its cell and leaving
 * a visible gap with the wrong background color.
 *
 * Fix: Apply modifier directly to Surface so weight()/widthIn() work correctly.
 */
@Composable
fun FeatureCard(
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
        shadowElevation = 2.dp,
    ) {
        Column(
            modifier = Modifier.padding(SiteDimens.CardPadding),
            verticalArrangement = Arrangement.spacedBy(SiteDimens.CardItemSpacing),
        ) {
            Surface(
                color = extra.accentBar.copy(alpha = 0.12f),
                shape = RoundedCornerShape(SiteDimens.FeatureIconBgCorner),
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = extra.accentBar,
                    modifier =
                        Modifier
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
