package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.segnities007.cmp_form_validation.site.LocalExtraColors
import com.segnities007.cmp_form_validation.site.SitePreviewTheme
import com.segnities007.cmp_form_validation.site.components.CtaSection
import com.segnities007.cmp_form_validation.site.components.SectionContainer
import com.segnities007.cmp_form_validation.site.pages.sections.HomeArchitectureSection
import com.segnities007.cmp_form_validation.site.pages.sections.HomePlatformSection
import com.segnities007.cmp_form_validation.site.pages.sections.HomeQuickStartSection
import com.segnities007.cmp_form_validation.site.pages.sections.HomeWhySection
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomePage(onNavigateToDocs: () -> Unit) {
    val extra = LocalExtraColors.current

    Column {
        HeroSection(onGetStarted = onNavigateToDocs)

        SectionContainer {
            HomeWhySection()
        }

        SectionContainer(backgroundColor = extra.sectionAltBg) {
            HomePlatformSection()
        }

        SectionContainer {
            HomeQuickStartSection()
        }

        SectionContainer(backgroundColor = extra.sectionAltBg) {
            HomeArchitectureSection()
        }

        CtaSection(onGetStarted = onNavigateToDocs)
    }
}

@Preview
@Composable
private fun HomePagePreview() {
    SitePreviewTheme {
        HomePage(onNavigateToDocs = {})
    }
}
