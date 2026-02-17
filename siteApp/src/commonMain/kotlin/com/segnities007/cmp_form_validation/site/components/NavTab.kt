package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

private val ActiveTabBackgroundAlpha = 0.08f
private val CompactTabPaddingH = 12.dp
private val CompactTabPaddingV = 6.dp
private val CompactTabFontSize = 13.sp
private val IndicatorWidth = 32.dp
private val IndicatorHeight = 2.5.dp
private val IndicatorCornerRadius = 1.dp
private val IndicatorSpacerHeight = 4.dp

@Composable
internal fun NavTab(
    label: String,
    isActive: Boolean,
    compact: Boolean = false,
    onClick: () -> Unit,
) {
    val activeBg = MaterialTheme.colorScheme.primary.copy(alpha = ActiveTabBackgroundAlpha)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isActive) activeBg else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(
                horizontal = if (compact) CompactTabPaddingH else SiteDimens.NavTabPaddingH,
                vertical = if (compact) CompactTabPaddingV else SiteDimens.NavTabPaddingV,
            ),
    ) {
        Text(
            text = label,
            style = if (compact) {
                MaterialTheme.typography.bodySmall.copy(fontSize = CompactTabFontSize)
            } else {
                MaterialTheme.typography.bodyMedium
            },
            fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal,
            color = if (isActive) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(IndicatorSpacerHeight))
        Box(
            Modifier
                .width(if (isActive) IndicatorWidth else 0.dp)
                .height(IndicatorHeight)
                .background(
                    if (isActive) MaterialTheme.colorScheme.primary else Color.Transparent,
                    RoundedCornerShape(IndicatorCornerRadius),
                ),
        )
    }
}

@Preview
@Composable
private fun NavTabPreview() {
    SitePreviewTheme {
        Row {
            NavTab(label = "Home", isActive = true, onClick = {})
            NavTab(label = "Examples", isActive = false, compact = true, onClick = {})
        }
    }
}
