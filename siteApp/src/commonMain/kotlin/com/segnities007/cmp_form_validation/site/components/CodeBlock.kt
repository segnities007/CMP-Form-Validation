package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CodeBlock(code: String, label: String? = null) {
    val extra = LocalExtraColors.current
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = extra.codeBlock.background,
        shape = RoundedCornerShape(SiteDimens.CodeBlockCorner),
    ) {
        Column {
            if (label != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(extra.codeBlock.border.copy(alpha = 0.15f))
                        .padding(
                            horizontal = SiteDimens.CodeBlockLabelPaddingH,
                            vertical = SiteDimens.CodeBlockLabelPaddingV,
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(SiteDimens.CodeBlockLabelGap),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Code,
                        contentDescription = null,
                        modifier = Modifier.size(SiteDimens.CodeBlockLabelIconSize),
                        tint = extra.codeBlock.border,
                    )
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        color = extra.codeBlock.border,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
            Row {
                Box(
                    Modifier
                        .width(SiteDimens.CodeBlockBorderWidth)
                        .background(
                            extra.codeBlock.border,
                            RoundedCornerShape(
                                topStart = if (label == null) SiteDimens.CodeBlockCorner else 0.dp,
                                bottomStart = SiteDimens.CodeBlockCorner,
                            ),
                        ),
                )
                Text(
                    text = code,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                    fontFamily = FontFamily.Monospace,
                    color = extra.codeBlock.text,
                    lineHeight = 20.sp,
                    modifier = Modifier.fillMaxWidth().padding(SiteDimens.CodeBlockPadding),
                )
            }
        }
    }
}

@Preview
@Composable
private fun CodeBlockPreview() {
    SitePreviewTheme {
        CodeBlock(
            code = "val x = 1\nprintln(x)",
            label = "Kotlin",
        )
    }
}

@Preview
@Composable
private fun CodeBlockNoLabelPreview() {
    SitePreviewTheme {
        CodeBlock(code = "val x = 1")
    }
}
