package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme

@Composable
fun ApiRow(
    name: String,
    description: String,
    signature: String? = null,
) {
    BoxWithConstraints {
        val compact = maxWidth < 700.dp

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f),
            shape = RoundedCornerShape(SiteDimens.ApiRowCorner),
        ) {
            Column(
                modifier = Modifier.padding(SiteDimens.ApiRowPadding),
                verticalArrangement = Arrangement.spacedBy(SiteDimens.ApiRowItemSpacing),
            ) {
                if (!compact) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(SiteDimens.ApiRowNameGap),
                    ) {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.width(SiteDimens.ApiRowNameWidth),
                        )
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(1f),
                            lineHeight = 22.sp,
                        )
                    }
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 22.sp,
                        )
                    }
                }

                if (signature != null) {
                    Text(
                        text = signature,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        lineHeight = 18.sp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ApiRowPreview() {
    SitePreviewTheme {
        ApiRow(
            name = "Rule<T>",
            description = "Single validation rule",
            signature = "fun interface Rule<T> { fun validate(value: T): ValidationError? }",
        )
    }
}

@Preview
@Composable
private fun ApiRowNoSignaturePreview() {
    SitePreviewTheme {
        ApiRow(
            name = "email(message)",
            description = "Validates email format",
        )
    }
}
