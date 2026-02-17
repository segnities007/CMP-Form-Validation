package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.segnities007.cmp_form_validation.site.components.CenteredContent
import com.segnities007.cmp_form_validation.site.components.CodeBlock
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExamplesPage() {
    Column {
        Spacer(Modifier.height(48.dp))
        CenteredContent {
            Text(stringResource(Res.string.examples_title), style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Spacer(Modifier.height(8.dp))
            Text(stringResource(Res.string.examples_subtitle), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Spacer(Modifier.height(40.dp))
        CenteredContent {
            Column(verticalArrangement = Arrangement.spacedBy(40.dp)) {
                ExampleSection(stringResource(Res.string.ex_email_title), stringResource(Res.string.ex_email_desc), CODE_EMAIL)
                ExampleSection(stringResource(Res.string.ex_login_title), stringResource(Res.string.ex_login_desc), CODE_LOGIN)
                ExampleSection(stringResource(Res.string.ex_signup_title), stringResource(Res.string.ex_signup_desc), CODE_SIGNUP)
                ExampleSection(stringResource(Res.string.ex_custom_title), stringResource(Res.string.ex_custom_desc), CODE_CUSTOM)
                ExampleSection(stringResource(Res.string.ex_modifier_title), stringResource(Res.string.ex_modifier_desc), CODE_MODIFIER)
                ExampleSection(stringResource(Res.string.ex_wrapper_title), stringResource(Res.string.ex_wrapper_desc), CODE_WRAPPER)
            }
        }
        Spacer(Modifier.height(72.dp))
    }
}

@Composable
private fun ExampleSection(title: String, description: String, code: String) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionHeader(title)
        Text(description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        CodeBlock(code, label = "Kotlin")
    }
}

private val CODE_EMAIL = """
@Composable
fun EmailFieldExample() {
    val email = rememberValidatedField(
        rules = persistentListOf(required(), email())
    )

    OutlinedTextField(
        value = email.value,
        onValueChange = email::onValueChange,
        label = { Text("Email") },
        isError = email.showErrors && !email.result.isValid,
        supportingText = { ValidationSupportingText(email) },
        modifier = Modifier.fillMaxWidth().validation(email)
    )
}
""".trimIndent()

private val CODE_LOGIN = """
@Composable
fun LoginFormExample() {
    val emailField = rememberValidatedField(
        rules = persistentListOf(required(), email())
    )
    val passwordField = rememberValidatedField(
        rules = persistentListOf(required(), minLength(8))
    )

    val form = rememberValidatedStringForm(
        fields = persistentMapOf(
            "email" to emailField,
            "password" to passwordField
        )
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = emailField.value,
            onValueChange = emailField::onValueChange,
            label = { Text("Email") },
            isError = emailField.showErrors && !emailField.result.isValid,
            supportingText = { ValidationSupportingText(emailField) },
            modifier = Modifier.fillMaxWidth().validation(emailField)
        )

        OutlinedTextField(
            value = passwordField.value,
            onValueChange = passwordField::onValueChange,
            label = { Text("Password") },
            isError = passwordField.showErrors && !passwordField.result.isValid,
            supportingText = { ValidationSupportingText(passwordField) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().validation(passwordField)
        )

        Button(
            onClick = { if (form.submit()) { /* navigate */ } },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log In")
        }
    }
}
""".trimIndent()

private val CODE_SIGNUP = """
@Composable
fun SignUpFormExample() {
    val emailField = rememberValidatedField(
        rules = persistentListOf(required(), email())
    )
    val passwordField = rememberValidatedField(
        rules = persistentListOf(required(), minLength(8))
    )
    val confirmField = rememberValidatedField(
        rules = persistentListOf(required())
    )

    val form = rememberValidatedStringForm(
        fields = persistentMapOf(
            "email" to emailField,
            "password" to passwordField,
            "confirmPassword" to confirmField
        ),
        formRules = persistentListOf(
            fieldsMatchRule(
                leftField = "confirmPassword",
                rightField = "password",
                message = "Passwords do not match."
            )
        )
    )

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // ... OutlinedTextField for each field ...

        // Display cross-field errors
        form.formErrors.forEach { error ->
            Text(
                text = error.defaultMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Button(onClick = { if (form.submit()) { /* navigate */ } }) {
            Text("Sign Up")
        }
    }
}
""".trimIndent()

private val CODE_CUSTOM = """
fun passwordStrength(
    minLen: Int = 8,
    message: String = "Password is too weak.",
): Rule<String> = Rule { value ->
    val checks = listOf(
        value.length >= minLen,
        value.any { it.isUpperCase() },
        value.any { it.isDigit() },
    )
    if (checks.all { it }) null
    else ValidationError(
        code = "password_strength",
        defaultMessage = message,
        meta = persistentMapOf(
            "minLen" to (value.length >= minLen).toString(),
            "uppercase" to value.any { it.isUpperCase() }.toString(),
            "digit" to value.any { it.isDigit() }.toString(),
        )
    )
}

val password = rememberValidatedField(
    rules = persistentListOf(required(), passwordStrength())
)
""".trimIndent()

private val CODE_MODIFIER = """
@Composable
fun ModifierExample() {
    val name = rememberValidatedField(
        trigger = ValidationTrigger.OnBlur,
        rules = persistentListOf(required(), minLength(2))
    )

    OutlinedTextField(
        value = name.value,
        onValueChange = name::onValueChange,
        label = { Text("Name") },
        isError = name.showErrors && !name.result.isValid,
        supportingText = { ValidationSupportingText(name) },
        modifier = Modifier
            .fillMaxWidth()
            .validation(name, validateOnBlur = true)
    )
}
""".trimIndent()

private val CODE_WRAPPER = """
@Composable
fun WrapperExample() {
    val email = rememberValidatedField(
        rules = persistentListOf(required(), email())
    )

    ValidatedOutlinedTextField(
        field = email,
        label = { Text("Email") },
        placeholder = { Text("you@example.com") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}
""".trimIndent()
