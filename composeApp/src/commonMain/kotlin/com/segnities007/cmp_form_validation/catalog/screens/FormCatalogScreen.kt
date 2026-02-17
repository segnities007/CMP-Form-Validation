package com.segnities007.cmp_form_validation.catalog.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.segnities007.cmp_form_validation.catalog.components.CatalogHero
import com.segnities007.cmp_form_validation.catalog.components.CatalogLazyColumn
import com.segnities007.cmp_form_validation.catalog.components.CatalogSection
import com.segnities007.cmp_form_validation.catalog.components.RuleChips
import com.segnities007.cmp_form_validation.catalog.screens.components.FormActionButtons
import com.segnities007.cmp_form_validation.catalog.screens.components.FormCrossFieldErrors
import com.segnities007.cmp_form_validation.catalog.screens.components.FormSubmissionStatus
import com.segnities007.cmp_form_validation.catalog.screens.components.FormValidatedField
import com.segnities007.cmp_form_validation.validation.Rule
import com.segnities007.cmp_form_validation.validation.ValidationError
import com.segnities007.cmp_form_validation.validation.compose.ComposeValidatedField
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedField
import com.segnities007.cmp_form_validation.validation.compose.rememberValidatedStringForm
import com.segnities007.cmp_form_validation.validation.email
import com.segnities007.cmp_form_validation.validation.fieldsMatchRule
import com.segnities007.cmp_form_validation.validation.maxLength
import com.segnities007.cmp_form_validation.validation.minLength
import com.segnities007.cmp_form_validation.validation.required
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf

/** Catalog screen demonstrating form-level submission and cross-field validation. */
@Composable
fun FormCatalogScreen(innerPadding: PaddingValues) {
    val nameField =
        rememberValidatedField(
            initialValue = "",
            rules = persistentListOf(required(), minLength(2), maxLength(30)),
        )
    val emailField =
        rememberValidatedField(
            initialValue = "",
            rules = persistentListOf(required(), email()),
        )
    val passwordField =
        rememberValidatedField(
            initialValue = "",
            rules =
                persistentListOf(
                    required(),
                    minLength(8),
                    requireDigitRule(),
                ),
        )
    val confirmField =
        rememberValidatedField(
            initialValue = "",
            rules = persistentListOf(required()),
        )
    var submitted by remember { mutableStateOf(false) }
    var isFormValid by remember { mutableStateOf(false) }
    val form =
        rememberValidatedStringForm(
            fields =
                persistentMapOf(
                    FIELD_NAME to nameField,
                    FIELD_EMAIL to emailField,
                    FIELD_PASSWORD to passwordField,
                    FIELD_CONFIRM_PASSWORD to confirmField,
                ),
            formRules =
                persistentListOf(
                    fieldsMatchRule(
                        leftField = FIELD_CONFIRM_PASSWORD,
                        rightField = FIELD_PASSWORD,
                        code = "password_mismatch",
                        message = "Password confirmation does not match.",
                    ),
                ),
        )
    val passwordVisualTransformation = PasswordVisualTransformation()
    val fields =
        listOf(
            FormFieldUiSpec(
                label = "Name",
                idleText = "2 to 30 chars",
                field = nameField,
            ),
            FormFieldUiSpec(
                label = "Email",
                idleText = "name@example.com",
                field = emailField,
            ),
            FormFieldUiSpec(
                label = "Password",
                idleText = "At least 8 chars + number",
                field = passwordField,
                visualTransformation = passwordVisualTransformation,
            ),
            FormFieldUiSpec(
                label = "Confirm password",
                idleText = "Must match password",
                field = confirmField,
                visualTransformation = passwordVisualTransformation,
            ),
        )
    val revalidateCrossFieldOnEdit = {
        if (submitted) {
            form.revalidateCrossField()
        }
    }

    CatalogLazyColumn(innerPadding = innerPadding) {
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
                fields.forEach { spec ->
                    FormValidatedField(
                        label = spec.label,
                        idleText = spec.idleText,
                        field = spec.field,
                        visualTransformation = spec.visualTransformation,
                        onEdited = revalidateCrossFieldOnEdit,
                    )
                }

                if (submitted && form.formErrors.isNotEmpty()) {
                    FormCrossFieldErrors(errors = form.formErrors)
                }

                FormActionButtons(
                    onValidate = {
                        submitted = true
                        isFormValid = form.submit()
                    },
                    onReset = {
                        form.reset()
                        submitted = false
                        isFormValid = false
                    },
                )

                if (submitted) {
                    FormSubmissionStatus(isFormValid = isFormValid)
                }
            }
        }
    }
}

private data class FormFieldUiSpec(
    val label: String,
    val idleText: String,
    val field: ComposeValidatedField<String>,
    val visualTransformation: VisualTransformation = VisualTransformation.None,
)

private const val FIELD_NAME = "name"
private const val FIELD_EMAIL = "email"
private const val FIELD_PASSWORD = "password"
private const val FIELD_CONFIRM_PASSWORD = "confirmPassword"

private fun requireDigitRule(
    code: String = "password_number",
    message: String = "Password must include at least one number.",
): Rule<String> =
    Rule { value ->
        if (value.any(Char::isDigit)) {
            null
        } else {
            ValidationError(
                code = code,
                defaultMessage = message,
            )
        }
    }

@Preview
@Composable
private fun FormCatalogScreenPreview() {
    MaterialTheme {
        FormCatalogScreen(PaddingValues())
    }
}
