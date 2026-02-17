package com.segnities007.cmp_form_validation.catalog.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.catalog.components.CatalogSection
import com.segnities007.cmp_form_validation.catalog.components.RuleChips
import com.segnities007.cmp_form_validation.validation.compose.ComposeValidatedField
import com.segnities007.cmp_form_validation.validation.compose.ValidatedOutlinedTextField
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.required
import kotlinx.collections.immutable.persistentListOf

@Composable
fun WrapperPatternSection(field: ComposeValidatedField<String>) {
    CatalogSection(
        title = "Wrapper-based (Supplementary)",
        description = "Use a ready-made field wrapper for compact code.",
    ) {
        RuleChips("ValidatedOutlinedTextField", "Boilerplate reduction")
        ValidatedOutlinedTextField(
            field = field,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Password") },
            idleSupportingText = "Good for rapid screen construction",
        )
    }
}

@Preview
@Composable
private fun WrapperPatternSectionPreview() {
    val field = rememberValidatedField(
        initialValue = "",
        rules = persistentListOf(required(), minLength(8)),
    )
    MaterialTheme {
        WrapperPatternSection(field = field)
    }
}
