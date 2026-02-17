package com.segnities007.cmp_form_validation.site.pages.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.NoteBox
import com.segnities007.cmp_form_validation.site.components.TipBox
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.tip_cross_field
import com.segnities007.cmp_form_validation.site.resources.tip_error_codes
import com.segnities007.cmp_form_validation.site.resources.tip_immutable
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DocsTipsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(SiteDimens.ApiRowInSectionSpacing)) {
        TipBox(stringResource(Res.string.tip_immutable))
        NoteBox(stringResource(Res.string.tip_cross_field))
        TipBox(stringResource(Res.string.tip_error_codes))
    }
}

@Preview
@Composable
private fun DocsTipsSectionPreview() {
    SitePreviewTheme {
        DocsTipsSection()
    }
}
