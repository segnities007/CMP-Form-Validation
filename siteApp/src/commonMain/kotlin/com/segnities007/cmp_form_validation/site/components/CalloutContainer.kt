package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme

@Composable
internal fun CalloutContainer(
    text: String,
    icon: ImageVector,
    tint: Color,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = tint.copy(alpha = 0.08f),
        shape = RoundedCornerShape(SiteDimens.CalloutCorner),
    ) {
        Row(
            modifier = Modifier.padding(SiteDimens.CalloutPadding),
            horizontalArrangement = Arrangement.spacedBy(SiteDimens.CalloutGap),
            verticalAlignment = Alignment.Top,
        ) {
            Icon(icon, null, tint = tint, modifier = Modifier.size(SiteDimens.CalloutIconSize))
            Text(
                text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp,
            )
        }
    }
}

@Preview
@Composable
private fun CalloutContainerPreview() {
    SitePreviewTheme {
        CalloutContainer(
            text = "Shared callout container preview.",
            icon = Icons.Rounded.Info,
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}
