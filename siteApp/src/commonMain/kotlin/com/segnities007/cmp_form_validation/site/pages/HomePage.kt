package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.segnities007.cmp_form_validation.site.SiteDimens
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.pages.sections.HomeArchitectureSection
import com.segnities007.cmp_form_validation.site.pages.sections.HomePlatformSection
import com.segnities007.cmp_form_validation.site.pages.sections.HomeQuickStartSection
import com.segnities007.cmp_form_validation.site.pages.sections.HomeWhySection
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomePage(onNavigateToDocs: () -> Unit) {
    Column {
        HeroSection(onGetStarted = onNavigateToDocs)

        Spacer(Modifier.height(SiteDimens.Section))
        HomeWhySection()

        Spacer(Modifier.height(SiteDimens.Section))
        HomePlatformSection()

        Spacer(Modifier.height(SiteDimens.Section))
        HomeQuickStartSection()

        Spacer(Modifier.height(SiteDimens.Section))
        HomeArchitectureSection()
        Spacer(Modifier.height(SiteDimens.PageBottom))
    }
}

@Preview
@Composable
private fun HomePagePreview() {
    SitePreviewTheme {
        HomePage(onNavigateToDocs = {})
    }
}
