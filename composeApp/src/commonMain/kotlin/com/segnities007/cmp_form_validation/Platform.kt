package com.segnities007.cmp_form_validation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
