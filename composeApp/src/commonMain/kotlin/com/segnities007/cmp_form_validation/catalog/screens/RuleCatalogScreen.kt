package com.segnities007.cmp_form_validation.catalog.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.catalog.components.CatalogHero
import com.segnities007.cmp_form_validation.catalog.components.CatalogLazyColumn
import com.segnities007.cmp_form_validation.catalog.screens.components.RuleSampleSection
import com.segnities007.cmp_form_validation.validation.compose.ComposeValidatedField
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import com.segnities007.cmp_form_validation.validation.email
import com.segnities007.cmp_form_validation.validation.maxLength
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.pattern
import com.segnities007.cmp_form_validation.validation.required
import kotlinx.collections.immutable.persistentListOf

/** Catalog screen that demonstrates core built-in rules one-by-one. */
@Composable
fun RuleCatalogScreen(innerPadding: PaddingValues) {
    val nicknameField =
        rememberValidatedField(
            rules = persistentListOf(required(), minLength(3), maxLength(20)),
        )
    val emailField =
        rememberValidatedField(
            rules = persistentListOf(required(), email()),
        )
    val postalCodeField =
        rememberValidatedField(
            rules =
                persistentListOf(
                    required(),
                    pattern(Regex("^\\d{3}-\\d{4}$"), message = "Use format like 123-4567."),
                ),
        )
    val samples =
        listOf(
            RuleSampleItem(
                title = "Nickname",
                description = "required + minLength(3) + maxLength(20)",
                placeholder = "your nickname",
                field = nicknameField,
                chips = listOf("required", "minLength(3)", "maxLength(20)"),
            ),
            RuleSampleItem(
                title = "Email",
                description = "required + email",
                placeholder = "name@example.com",
                field = emailField,
                chips = listOf("required", "email"),
            ),
            RuleSampleItem(
                title = "Postal code",
                description = "required + pattern(^\\d{3}-\\d{4}$)",
                placeholder = "123-4567",
                field = postalCodeField,
                chips = listOf("required", "pattern"),
            ),
        )

    CatalogLazyColumn(innerPadding = innerPadding) {
        item {
            CatalogHero(
                title = "Rule Playground",
                subtitle = "Inspect each built-in rule behavior independently.",
            )
        }
        samples.forEach { sample ->
            item {
                RuleSampleSection(
                    title = sample.title,
                    description = sample.description,
                    placeholder = sample.placeholder,
                    field = sample.field,
                    *sample.chips.toTypedArray(),
                )
            }
        }
    }
}

private data class RuleSampleItem(
    val title: String,
    val description: String,
    val placeholder: String,
    val field: ComposeValidatedField<String>,
    val chips: List<String>,
)

@Preview
@Composable
private fun RuleCatalogScreenPreview() {
    MaterialTheme {
        RuleCatalogScreen(PaddingValues())
    }
}
