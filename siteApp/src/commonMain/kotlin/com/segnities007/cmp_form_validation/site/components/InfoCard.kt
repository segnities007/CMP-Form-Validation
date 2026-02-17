package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InfoCard(title: String, body: String) {
    BoxWithConstraints {
        val compact = maxWidth < 320.dp
        val padding = if (compact) SiteDimens.CompactCardPadding else SiteDimens.InfoCardPadding
        val extra = LocalExtraColors.current

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = SiteDimens.InfoCardMinHeight),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
            shape = RoundedCornerShape(SiteDimens.InfoCardCorner),
            border = BorderStroke(SiteDimens.CardBorderWidth, extra.cardBorder),
        ) {
            Column(
                modifier = Modifier.padding(padding),
                verticalArrangement = Arrangement.spacedBy(SiteDimens.InfoCardItemSpacing),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 22.sp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun InfoCardPreview() {
    SitePreviewTheme {
        InfoCard(
            title = "validation-core",
            body = "Core validation logic independent of any UI framework.",
        )
    }
}
