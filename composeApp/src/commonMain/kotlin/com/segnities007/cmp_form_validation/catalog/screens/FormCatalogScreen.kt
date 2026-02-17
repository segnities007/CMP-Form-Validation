package com.segnities007.cmp_form_validation.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.catalog.components.CatalogHero
import com.segnities007.cmp_form_validation.catalog.components.CatalogSection
import com.segnities007.cmp_form_validation.catalog.components.RuleChips
import com.segnities007.cmp_form_validation.validation.Rule
import com.segnities007.cmp_form_validation.validation.ValidationError
import com.segnities007.cmp_form_validation.validation.email
import com.segnities007.cmp_form_validation.validation.fieldsMatchRule
import com.segnities007.cmp_form_validation.validation.maxLength
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.required
import com.segnities007.cmp_form_validation.validation.compose.ValidationSupportingText
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedStringForm
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf

/** Catalog screen demonstrating form-level submission and cross-field validation. */
@Composable
fun FormCatalogScreen(innerPadding: PaddingValues) {
    val nameField = rememberValidatedField(
        initialValue = "",
        rules = persistentListOf(required(), minLength(2), maxLength(30)),
    )
    val emailField = rememberValidatedField(
        initialValue = "",
        rules = persistentListOf(required(), email()),
    )
    val passwordField = rememberValidatedField(
        initialValue = "",
        rules = persistentListOf(
            required(),
            minLength(8),
            Rule<String> { value ->
                if (value.any(Char::isDigit)) null else ValidationError(
                    code = "password_number",
                    defaultMessage = "Password must include at least one number.",
                )
            },
        ),
    )
    val confirmField = rememberValidatedField(
        initialValue = "",
        rules = persistentListOf(required()),
    )
    val form = rememberValidatedStringForm(
        fields = persistentMapOf(
            "name" to nameField,
            "email" to emailField,
            "password" to passwordField,
            "confirmPassword" to confirmField,
        ),
        formRules = persistentListOf(
            fieldsMatchRule(
                leftField = "confirmPassword",
                rightField = "password",
                code = "password_mismatch",
                message = "Password confirmation does not match.",
            ),
        ),
    )

    var submitted by remember { mutableStateOf(false) }
    var isFormValid by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            CatalogHero(
                title = "Form Validation Flow",
                subtitle = "Submit-first UX with cross-field revalidation.",
            )
        }
        item {
            CatalogSection(
                title = "Sign-up Form",
                description = "Submit-first validation, then validate as user edits.",
            ) {
                RuleChips("onSubmitThenChange", "fieldsMatchRule")
                OutlinedTextField(
                    value = nameField.value,
                    onValueChange = {
                        nameField.onValueChange(it)
                        if (submitted) form.revalidateCrossField()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    label = { Text("Name") },
                    isError = nameField.showErrors && !nameField.result.isValid,
                    supportingText = { ValidationSupportingText(nameField, idleText = "2 to 30 chars") },
                )

                OutlinedTextField(
                    value = emailField.value,
                    onValueChange = {
                        emailField.onValueChange(it)
                        if (submitted) form.revalidateCrossField()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    label = { Text("Email") },
                    isError = emailField.showErrors && !emailField.result.isValid,
                    supportingText = { ValidationSupportingText(emailField, idleText = "name@example.com") },
                )

                OutlinedTextField(
                    value = passwordField.value,
                    onValueChange = {
                        passwordField.onValueChange(it)
                        if (submitted) form.revalidateCrossField()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    label = { Text("Password") },
                    isError = passwordField.showErrors && !passwordField.result.isValid,
                    supportingText = { ValidationSupportingText(passwordField, idleText = "At least 8 chars + number") },
                )

                OutlinedTextField(
                    value = confirmField.value,
                    onValueChange = {
                        confirmField.onValueChange(it)
                        if (submitted) form.revalidateCrossField()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    label = { Text("Confirm password") },
                    isError = confirmField.showErrors && !confirmField.result.isValid,
                    supportingText = { ValidationSupportingText(confirmField, idleText = "Must match password") },
                )

                if (submitted && form.formErrors.isNotEmpty()) {
                    form.formErrors.forEach { error ->
                        Text(
                            text = error.defaultMessage,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = {
                            submitted = true
                            isFormValid = form.submit()
                        },
                    ) {
                        Text("Validate")
                    }
                    Button(
                        onClick = {
                            form.reset()
                            submitted = false
                            isFormValid = false
                        },
                    ) {
                        Text("Reset")
                    }
                }

                if (submitted) {
                    Text(
                        text = if (isFormValid) "Form is valid. Ready to submit." else "Form has validation errors.",
                        color = if (isFormValid) Color(0xFF1B5E20) else MaterialTheme.colorScheme.error,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FormCatalogScreenPreview() {
    MaterialTheme {
        FormCatalogScreen(PaddingValues())
    }
}
