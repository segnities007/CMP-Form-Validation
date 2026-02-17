package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikepenz.markdown.compose.components.markdownComponents
import com.mikepenz.markdown.compose.elements.highlightedCodeBlock
import com.mikepenz.markdown.compose.elements.highlightedCodeFence
import com.mikepenz.markdown.m3.Markdown
import com.mikepenz.markdown.m3.markdownColor
import com.mikepenz.markdown.m3.markdownTypography
import com.mikepenz.markdown.model.markdownPadding
import com.mikepenz.markdown.model.rememberMarkdownState
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme

@Composable
fun CodeBlock(
    code: String,
    label: String? = null,
) {
    val extra = LocalExtraColors.current
    val codeMarkdown = "```kotlin\n$code\n```"
    val markdownState =
        rememberMarkdownState(
            content = codeMarkdown,
            immediate = true,
        )
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = extra.codeBlock.background,
        shape = RoundedCornerShape(SiteDimens.CodeBlockCorner),
    ) {
        Column {
            if (label != null) {
                Row(
                    modifier =
                        Modifier
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
            Row(Modifier.height(IntrinsicSize.Min)) {
                Box(
                    Modifier
                        .width(SiteDimens.CodeBlockBorderWidth)
                        .fillMaxHeight()
                        .background(
                            extra.codeBlock.border,
                            RoundedCornerShape(
                                topStart = if (label == null) SiteDimens.CodeBlockCorner else 0.dp,
                                bottomStart = SiteDimens.CodeBlockCorner,
                            ),
                        ),
                )
                Markdown(
                    markdownState = markdownState,
                    colors =
                        markdownColor(
                            text = extra.codeBlock.text,
                            codeBackground = extra.codeBlock.background,
                            inlineCodeBackground = extra.codeBlock.background,
                        ),
                    typography =
                        markdownTypography(
                            code =
                                MaterialTheme.typography.bodyMedium.copy(
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 13.sp,
                                    lineHeight = 20.sp,
                                    color = extra.codeBlock.text,
                                ),
                        ),
                    padding =
                        markdownPadding(
                            block = 0.dp,
                            codeBlock = PaddingValues(0.dp),
                        ),
                    components =
                        markdownComponents(
                            codeBlock = highlightedCodeBlock,
                            codeFence = highlightedCodeFence,
                        ),
                    loading = { loadingModifier ->
                        Text(
                            text = code,
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                            fontFamily = FontFamily.Monospace,
                            color = extra.codeBlock.text,
                            lineHeight = 20.sp,
                            softWrap = false,
                            modifier =
                                loadingModifier
                                    .fillMaxWidth()
                                    .heightIn(min = 44.dp)
                                    .horizontalScroll(rememberScrollState())
                                    .padding(SiteDimens.CodeBlockPadding),
                        )
                    },
                    error = { errorModifier ->
                        Text(
                            text = code,
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
                            fontFamily = FontFamily.Monospace,
                            color = extra.codeBlock.text,
                            lineHeight = 20.sp,
                            softWrap = false,
                            modifier =
                                errorModifier
                                    .fillMaxWidth()
                                    .heightIn(min = 44.dp)
                                    .horizontalScroll(rememberScrollState())
                                    .padding(SiteDimens.CodeBlockPadding),
                        )
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .heightIn(min = 44.dp)
                            .padding(SiteDimens.CodeBlockPadding),
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
