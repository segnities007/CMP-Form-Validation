package com.segnities007.cmp_form_validation

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() =
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Cmpformvalidation",
        ) {
            App()
        }
    }
