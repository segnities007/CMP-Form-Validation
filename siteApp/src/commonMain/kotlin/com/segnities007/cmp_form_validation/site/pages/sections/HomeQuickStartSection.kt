package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.CodeBlock
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import com.segnities007.cmp_form_validation.site.pages.CodeSamples
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.quick_start_desc
import com.segnities007.cmp_form_validation.site.resources.quick_start_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeQuickStartSection() {
    SectionHeader(stringResource(Res.string.quick_start_title))
    Spacer(Modifier.height(SiteDimens.SubSectionContentGap))
    Text(
        text = stringResource(Res.string.quick_start_desc),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
    Spacer(Modifier.height(SiteDimens.CardPadding))
    CodeBlock(CodeSamples.QUICK_START, label = "Kotlin")
}

@Preview
@Composable
private fun HomeQuickStartSectionPreview() {
    SitePreviewTheme {
        HomeQuickStartSection()
    }
}
