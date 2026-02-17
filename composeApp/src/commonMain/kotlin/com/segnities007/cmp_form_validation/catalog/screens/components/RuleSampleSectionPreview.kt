package com.segnities007.cmp_form_validation.catalog.screens.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import com.segnities007.cmp_form_validation.validation.maxLength
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.required
import kotlinx.collections.immutable.persistentListOf

@Preview
@Composable
private fun RuleSampleSectionPreview() {
    val field = rememberValidatedField(
        rules = persistentListOf(required(), minLength(3), maxLength(20)),
    )
    MaterialTheme {
        RuleSampleSection(
            title = "Nickname",
            description = "required + minLength(3) + maxLength(20)",
            placeholder = "your nickname",
            field = field,
            "required",
            "minLength(3)",
            "maxLength(20)",
        )
    }
}
