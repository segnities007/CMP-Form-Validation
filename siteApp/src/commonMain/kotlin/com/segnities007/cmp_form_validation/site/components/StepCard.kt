package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

/**
 * A documentation step card with a left accent bar.
 *
 * Modifier ordering is intentional:
 * - The outer Surface applies border + shape + background color
 * - The inner Row uses IntrinsicSize.Min for the accent bar height
 * - The accent bar uses `.fillMaxHeight().width(4.dp).background(color)`
 *   so background is drawn AFTER sizing (correct order)
 */
@Composable
fun StepCard(
    stepTitle: String,
    stepDescription: String,
    code: String? = null,
    explanation: String? = null,
) {
    val extra = LocalExtraColors.current

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(SiteDimens.CardCorner),
        border = BorderStroke(SiteDimens.CardBorderWidth, extra.cardBorder),
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            // Left accent bar — background AFTER sizing
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp),
                color = extra.accentBar,
                shape = RoundedCornerShape(
                    topStart = SiteDimens.CardCorner,
                    bottomStart = SiteDimens.CardCorner,
                ),
            ) {}

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(SiteDimens.CardPadding),
                verticalArrangement = Arrangement.spacedBy(SiteDimens.CardItemSpacing),
            ) {
                SectionHeader(stepTitle)
                Text(
                    text = stepDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 22.sp,
                )
                if (code != null) {
                    CodeBlock(code, label = "Kotlin")
                }
                if (explanation != null) {
                    Text(
                        text = explanation,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                        lineHeight = 20.sp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun StepCardPreview() {
    SitePreviewTheme {
        StepCard(
            stepTitle = "Step 1: Add Dependencies",
            stepDescription = "Add the validation library to your project.",
            code = "implementation(\"com.example:lib:1.0\")",
            explanation = "This adds the core validation module.",
        )
    }
}
