package com.segnities007.cmp_form_validation.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.catalog.components.CatalogHero
import com.segnities007.cmp_form_validation.catalog.components.CatalogSection
import com.segnities007.cmp_form_validation.catalog.components.RuleChips
import com.segnities007.cmp_form_validation.validation.email
import com.segnities007.cmp_form_validation.validation.maxLength
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.pattern
import com.segnities007.cmp_form_validation.validation.required
import com.segnities007.cmp_form_validation.validation.compose.ValidationSupportingText
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import kotlinx.collections.immutable.persistentListOf

/** Catalog screen that demonstrates core built-in rules one-by-one. */
@Composable
fun RuleCatalogScreen(innerPadding: PaddingValues) {
    val nicknameField = rememberValidatedField(
        rules = persistentListOf(required(), minLength(3), maxLength(20)),
    )
    val emailField = rememberValidatedField(
        rules = persistentListOf(required(), email()),
    )
    val postalCodeField = rememberValidatedField(
        rules = persistentListOf(
            required(),
            pattern(Regex("^\\d{3}-\\d{4}$"), message = "Use format like 123-4567."),
        ),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            CatalogHero(
                title = "Rule Playground",
                subtitle = "Inspect each built-in rule behavior independently.",
            )
        }
        item {
            RuleSample(
                title = "Nickname",
                description = "required + minLength(3) + maxLength(20)",
                placeholder = "your nickname",
                field = nicknameField,
                chips = arrayOf("required", "minLength(3)", "maxLength(20)"),
            )
        }
        item {
            RuleSample(
                title = "Email",
                description = "required + email",
                placeholder = "name@example.com",
                field = emailField,
                chips = arrayOf("required", "email"),
            )
        }
        item {
            RuleSample(
                title = "Postal code",
                description = "required + pattern(^\\\\d{3}-\\\\d{4}$)",
                placeholder = "123-4567",
                field = postalCodeField,
                chips = arrayOf("required", "pattern"),
            )
        }
    }
}

@Composable
private fun RuleSample(
    title: String,
    description: String,
    placeholder: String,
    field: com.segnities007.cmp_form_validation.validation.compose.ComposeValidatedField<String>,
    chips: Array<String>,
) {
    CatalogSection(title = title, description = description) {
        RuleChips(*chips)
        OutlinedTextField(
            value = field.value,
            onValueChange = field::onValueChange,
            isError = field.showErrors && !field.result.isValid,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            label = { Text(title) },
            placeholder = { Text(placeholder) },
            supportingText = {
                ValidationSupportingText(
                    field = field,
                    idleText = "Type to validate",
                )
            },
        )
    }
}

@Preview
@Composable
private fun RuleCatalogScreenPreview() {
    MaterialTheme {
        RuleCatalogScreen(PaddingValues())
    }
}
