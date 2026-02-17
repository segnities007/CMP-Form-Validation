package com.segnities007.cmp_form_validation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.segnities007.cmp_form_validation.site.DocsSiteApp
import kotlinx.browser.window

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val currentLang = window.localStorage.getItem("site_locale")
        ?: window.navigator.language.take(2)

    ComposeViewport {
        DocsSiteApp(
            currentLocaleCode = currentLang,
            onLocaleToggle = {
                val next = if (currentLang.startsWith("ja")) "en" else "ja"
                window.localStorage.setItem("site_locale", next)
                window.location.reload()
            },
        )
    }
}
