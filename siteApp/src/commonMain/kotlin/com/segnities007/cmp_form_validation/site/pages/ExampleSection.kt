package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.CodeBlock
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ExampleSection(title: String, description: String, code: String) {
    Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.CardItemSpacing)) {
        SectionHeader(title)
        Text(
            description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        CodeBlock(code, label = "Kotlin")
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
