package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SidebarSectionLabel(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
        modifier = Modifier.padding(
            start = SiteDimens.SidebarItemPaddingH,
            top = SiteDimens.SidebarSectionLabelPaddingTop,
            bottom = SiteDimens.SidebarSectionLabelPaddingBottom,
        ),
    )
}

@Composable
fun SidebarNavItem(
    label: String,
    isActive: Boolean,
    onClick: () -> Unit,
) {
    val extra = LocalExtraColors.current
    val bgColor = if (isActive) extra.sidebarActiveBg else Color.Transparent
    val textColor = if (isActive) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.onSurfaceVariant

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(
                horizontal = SiteDimens.SidebarItemPaddingH,
                vertical = SiteDimens.SidebarItemPaddingV,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isActive) {
            Box(
                Modifier
                    .width(SiteDimens.SidebarActiveBarWidth)
                    .height(SiteDimens.SidebarActiveBarHeight)
                    .background(extra.sidebarActiveBar, RoundedCornerShape(2.dp)),
            )
            Spacer(Modifier.width(10.dp))
        }
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal,
            color = textColor,
        )
    }
}

@Preview
@Composable
private fun SidebarNavItemPreview() {
    SitePreviewTheme {
        Column {
            SidebarSectionLabel("GETTING STARTED")
            SidebarNavItem("1. Add Dependencies", isActive = true, onClick = {})
            SidebarNavItem("2. Create Field", isActive = false, onClick = {})
            SidebarNavItem("3. Connect to UI", isActive = false, onClick = {})
        }
    }
}
