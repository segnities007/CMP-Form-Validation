package com.segnities007.cmp_form_validation.catalog.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun RuleChipsPreview() {
    MaterialTheme {
        RuleChips("required", "minLength(3)", "email")
    }
}
