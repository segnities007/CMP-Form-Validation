package com.segnities007.cmp_form_validation.site.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.segnities007.cmp_form_validation.site.components.CenteredContent
import com.segnities007.cmp_form_validation.site.components.CodeBlock
import com.segnities007.cmp_form_validation.site.components.InfoCard
import com.segnities007.cmp_form_validation.site.components.NoteBox
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import com.segnities007.cmp_form_validation.site.components.StepCard
import com.segnities007.cmp_form_validation.site.components.TipBox
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun DocsPage() {
    Column {
        Spacer(Modifier.height(48.dp))
        CenteredContent {
            Text(stringResource(Res.string.docs_title), style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Spacer(Modifier.height(8.dp))
            Text(stringResource(Res.string.docs_subtitle), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(4.dp))
            Surface(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), shape = RoundedCornerShape(12.dp)) {
                Text(stringResource(Res.string.docs_time), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(Modifier.height(24.dp))
        CenteredContent {
            Text(stringResource(Res.string.docs_overview), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 22.sp)
        }

        Spacer(Modifier.height(40.dp))
        CenteredContent {
            Column(verticalArrangement = Arrangement.spacedBy(40.dp)) {
                StepCard(stringResource(Res.string.step1_title), stringResource(Res.string.step1_desc), CODE_ADD_DEPS, stringResource(Res.string.step1_note))
                StepCard(stringResource(Res.string.step2_title), stringResource(Res.string.step2_desc), CODE_CREATE_FIELD, stringResource(Res.string.step2_explain))
                StepCard(stringResource(Res.string.step3_title), stringResource(Res.string.step3_desc), CODE_BIND_UI, stringResource(Res.string.step3_explain))

                // Triggers
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionHeader(stringResource(Res.string.step4_title))
                    Text(stringResource(Res.string.step4_desc), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        InfoCard("OnChange", stringResource(Res.string.trigger_on_change_desc))
                        InfoCard("OnBlur", stringResource(Res.string.trigger_on_blur_desc))
                        InfoCard("OnSubmitThenChange", stringResource(Res.string.trigger_on_submit_desc))
                    }
                    CodeBlock(CODE_TRIGGER, label = "Kotlin")
                }

                // Strategies
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionHeader(stringResource(Res.string.step5_title))
                    Text(stringResource(Res.string.step5_desc), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        InfoCard("CollectAll", stringResource(Res.string.strategy_collect_all_desc))
                        InfoCard("FirstError", stringResource(Res.string.strategy_first_error_desc))
                    }
                    CodeBlock(CODE_STRATEGY, label = "Kotlin")
                }

                StepCard(stringResource(Res.string.step6_title), stringResource(Res.string.step6_desc), CODE_FORM, stringResource(Res.string.step6_explain))
                StepCard(stringResource(Res.string.step7_title), stringResource(Res.string.step7_desc), CODE_CUSTOM_RULE, stringResource(Res.string.step7_explain))

                // Integration styles
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionHeader(stringResource(Res.string.integration_title))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        InfoCard(stringResource(Res.string.integration_primary), stringResource(Res.string.integration_primary_desc))
                        InfoCard(stringResource(Res.string.integration_modifier), stringResource(Res.string.integration_modifier_desc))
                        InfoCard(stringResource(Res.string.integration_wrapper), stringResource(Res.string.integration_wrapper_desc))
                    }
                }

                // Tips
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    TipBox(stringResource(Res.string.tip_immutable))
                    NoteBox(stringResource(Res.string.tip_cross_field))
                    TipBox(stringResource(Res.string.tip_error_codes))
                }
            }
        }
        Spacer(Modifier.height(72.dp))
    }
}

private val CODE_ADD_DEPS = """
// build.gradle.kts (shared module)
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("com.segnities007:validation-core:<version>")
            implementation("com.segnities007:validation-compose:<version>")
        }
    }
}
""".trimIndent()

private val CODE_CREATE_FIELD = """
val emailField = rememberValidatedField(
    initialValue = "",
    trigger = ValidationTrigger.OnSubmitThenChange,
    strategy = ValidationStrategy.CollectAll,
    rules = persistentListOf(
        required(),
        email()
    )
)
""".trimIndent()

private val CODE_BIND_UI = """
OutlinedTextField(
    value = emailField.value,
    onValueChange = emailField::onValueChange,
    label = { Text("Email") },
    isError = emailField.showErrors && !emailField.result.isValid,
    supportingText = { ValidationSupportingText(emailField) },
    modifier = Modifier
        .fillMaxWidth()
        .validation(emailField)
)
""".trimIndent()

private val CODE_TRIGGER = """
// Real-time feedback
val username = rememberValidatedField(
    trigger = ValidationTrigger.OnChange,
    rules = persistentListOf(required(), minLength(3))
)

// Validate on focus loss
val address = rememberValidatedField(
    trigger = ValidationTrigger.OnBlur,
    rules = persistentListOf(required())
)

// Validate after first submit (default)
val comment = rememberValidatedField(
    trigger = ValidationTrigger.OnSubmitThenChange,
    rules = persistentListOf(maxLength(500))
)
""".trimIndent()

private val CODE_STRATEGY = """
// Show all errors at once
val password = rememberValidatedField(
    strategy = ValidationStrategy.CollectAll,
    rules = persistentListOf(
        required(),
        minLength(8),
        pattern(Regex("[A-Z]"), message = "Needs uppercase"),
        pattern(Regex("[0-9]"), message = "Needs digit")
    )
)

// Stop at first error
val code = rememberValidatedField(
    strategy = ValidationStrategy.FirstError,
    rules = persistentListOf(
        required(),
        pattern(Regex("^[0-9]{6}${'$'}"), message = "Must be 6 digits")
    )
)
""".trimIndent()

private val CODE_FORM = """
val passwordField = rememberValidatedField(
    rules = persistentListOf(required(), minLength(8))
)
val confirmField = rememberValidatedField(
    rules = persistentListOf(required())
)

val form = rememberValidatedStringForm(
    fields = persistentMapOf(
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

Button(onClick = {
    val isValid = form.submit()
    if (isValid) { /* proceed */ }
}) {
    Text("Submit")
}
""".trimIndent()

private val CODE_CUSTOM_RULE = """
fun passwordStrength(
    minLength: Int = 8,
    message: String = "Password is too weak.",
): Rule<String> = Rule { value ->
    val hasUppercase = value.any { it.isUpperCase() }
    val hasDigit = value.any { it.isDigit() }
    val hasMinLength = value.length >= minLength

    if (hasUppercase && hasDigit && hasMinLength) null
    else ValidationError(
        code = "password_strength",
        defaultMessage = message,
        meta = persistentMapOf(
            "hasUppercase" to hasUppercase.toString(),
            "hasDigit" to hasDigit.toString(),
            "hasMinLength" to hasMinLength.toString()
        )
    )
}

// Usage
val password = rememberValidatedField(
    rules = persistentListOf(required(), passwordStrength())
)
""".trimIndent()
