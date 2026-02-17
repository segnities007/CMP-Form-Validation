package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.CenteredContent
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.docs_overview
import com.segnities007.cmp_form_validation.site.resources.docs_subtitle
import com.segnities007.cmp_form_validation.site.resources.docs_time
import com.segnities007.cmp_form_validation.site.resources.docs_title
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DocsIntroSection() {
    CenteredContent {
        Text(
            stringResource(Res.string.docs_title),
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(Modifier.height(SiteDimens.SubSectionContentGap))
        Text(
            stringResource(Res.string.docs_subtitle),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(SiteDimens.SectionHeaderBottomPadding))
        Surface(
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
            shape = RoundedCornerShape(SiteDimens.CardCorner),
        ) {
            Text(
                stringResource(Res.string.docs_time),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
    Spacer(Modifier.height(SiteDimens.SectionContentGap))
    CenteredContent {
        Text(
            stringResource(Res.string.docs_overview),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 22.sp,
        )
    }
}

@Preview
@Composable
private fun DocsIntroSectionPreview() {
    SitePreviewTheme {
        DocsIntroSection()
    }
}
