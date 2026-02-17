package com.segnities007.cmp_form_validation.site.pages

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.CodeBlock
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ExampleSection(title: String, description: String, code: String) {
    BoxWithConstraints {
        val compact = maxWidth < 520.dp
        val padding = if (compact) SiteDimens.CompactCardPadding else SiteDimens.CardPadding

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = SiteDimens.ExampleCardMinHeight),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f),
            shape = RoundedCornerShape(SiteDimens.CardCorner),
        ) {
            Column(
                modifier = Modifier.padding(padding),
                verticalArrangement = Arrangement.spacedBy(SiteDimens.CardItemSpacing),
            ) {
                SectionHeader(title)
                Text(
                    description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 22.sp,
                )
                CodeBlock(code, label = "Kotlin")
            }
        }
    }
}

@Preview
@Composable
private fun ExampleSectionPreview() {
    SitePreviewTheme {
        ExampleSection(
            title = "Email Validation",
            description = "Basic email field with validation.",
            code = "val email = rememberValidatedField(\n    rules = persistentListOf(required(), email())\n)",
        )
    }
}
