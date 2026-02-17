package com.segnities007.cmp_form_validation.site.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StepCard(
    stepTitle: String,
    stepDescription: String,
    code: String? = null,
    explanation: String? = null,
) {
    Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.CardItemSpacing)) {
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
