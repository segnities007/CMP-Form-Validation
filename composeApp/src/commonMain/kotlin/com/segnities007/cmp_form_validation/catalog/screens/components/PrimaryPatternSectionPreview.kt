package com.segnities007.cmp_form_validation.catalog.screens.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import com.segnities007.cmp_form_validation.validation.email
import com.segnities007.cmp_form_validation.validation.required
import kotlinx.collections.immutable.persistentListOf

@Preview
@Composable
private fun PrimaryPatternSectionPreview() {
    val field = rememberValidatedField(
        initialValue = "",
        rules = persistentListOf(required(), email()),
    )
    MaterialTheme {
        PrimaryPatternSection(field = field)
    }
}
