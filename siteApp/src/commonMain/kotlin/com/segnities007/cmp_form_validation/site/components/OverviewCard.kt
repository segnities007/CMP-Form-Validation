package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import androidx.compose.ui.tooling.preview.Preview

data class OverviewMeta(
    val icon: ImageVector,
    val label: String,
    val value: String,
)

@Composable
fun OverviewCard(
    title: String,
    description: String,
    metaItems: List<OverviewMeta>,
    modifier: Modifier = Modifier,
    actionContent: @Composable (() -> Unit)? = null,
) {
    val extra = LocalExtraColors.current

    Surface(
        modifier = modifier.width(SiteDimens.OverviewCardWidth),
        color = extra.overviewCardBg,
        shape = RoundedCornerShape(SiteDimens.OverviewCardCorner),
        border = BorderStroke(SiteDimens.OverviewCardBorderWidth, extra.overviewCardBorder),
        shadowElevation = 2.dp,
    ) {
        Column(
            modifier = Modifier.padding(SiteDimens.OverviewCardPadding),
            verticalArrangement = Arrangement.spacedBy(SiteDimens.OverviewCardItemSpacing),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 18.sp,
            )

            if (metaItems.isNotEmpty()) {
                Spacer(Modifier.height(4.dp))
                Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.OverviewCardMetaSpacing)) {
                    metaItems.forEach { meta ->
                        OverviewMetaRow(meta)
                    }
                }
            }

            if (actionContent != null) {
                Spacer(Modifier.height(4.dp))
                actionContent()
            }
        }
    }
}

@Composable
private fun OverviewMetaRow(meta: OverviewMeta) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = meta.icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp),
        )
        Text(
            text = meta.label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = meta.value,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview
@Composable
private fun OverviewCardPreview() {
    SitePreviewTheme {
        OverviewCard(
            title = "Overview",
            description = "A guide to form validation.",
            metaItems = listOf(
                OverviewMeta(Icons.Rounded.AccessTime, "Reading Time", "~10 min"),
                OverviewMeta(Icons.Rounded.Layers, "Sections", "9"),
            ),
        )
    }
}
