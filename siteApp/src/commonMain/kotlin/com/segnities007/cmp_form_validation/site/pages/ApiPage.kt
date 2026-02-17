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
import com.segnities007.cmp_form_validation.site.components.ApiRow
import com.segnities007.cmp_form_validation.site.components.CenteredContent
import com.segnities007.cmp_form_validation.site.components.SectionHeader
import com.segnities007.cmp_form_validation.site.resources.Res
import com.segnities007.cmp_form_validation.site.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun ApiPage() {
    Column {
        Spacer(Modifier.height(48.dp))
        CenteredContent {
            Text(stringResource(Res.string.api_title), style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
            Spacer(Modifier.height(8.dp))
            Text(stringResource(Res.string.api_subtitle), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Spacer(Modifier.height(40.dp))
        CenteredContent {
            Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {
                // Core types
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionHeader(stringResource(Res.string.api_core_title))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        ApiRow("Rule<T>", stringResource(Res.string.api_rule_desc), "fun interface Rule<T> { fun validate(value: T): ValidationError? }")
                        ApiRow("Validator<T>", stringResource(Res.string.api_validator_desc), "class Validator<T>(rules: List<Rule<T>>, strategy: ValidationStrategy)")
                        ApiRow("ValidationResult", stringResource(Res.string.api_result_desc), "data class ValidationResult(errors: ImmutableList<ValidationError>)")
                        ApiRow("ValidationError", stringResource(Res.string.api_error_desc), "data class ValidationError(code: String, defaultMessage: String, path: String?, meta: ImmutableMap<String, String>)")
                        ApiRow("ValidationStrategy", stringResource(Res.string.api_strategy_desc))
                        ApiRow("ErrorCode", stringResource(Res.string.api_error_code_desc))
                        ApiRow("validatorOf()", stringResource(Res.string.api_validator_of_desc))
                    }
                }

                // Built-in rules
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionHeader(stringResource(Res.string.api_rules_title))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        ApiRow("required(trim, message)", stringResource(Res.string.api_required_desc))
                        ApiRow("minLength(min, message)", stringResource(Res.string.api_min_length_desc))
                        ApiRow("maxLength(max, message)", stringResource(Res.string.api_max_length_desc))
                        ApiRow("pattern(regex, code, message)", stringResource(Res.string.api_pattern_desc))
                        ApiRow("email(message)", stringResource(Res.string.api_email_desc))
                    }
                }

                // Field & Form types
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionHeader(stringResource(Res.string.api_field_title))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        ApiRow("ValidatedField<T>", stringResource(Res.string.api_validated_field_desc))
                        ApiRow("ValidationTrigger", stringResource(Res.string.api_trigger_desc))
                        ApiRow("ValidatedStringForm", stringResource(Res.string.api_form_desc))
                        ApiRow("FormRule", stringResource(Res.string.api_form_rule_desc))
                        ApiRow("fieldsMatchRule()", stringResource(Res.string.api_fields_match_desc))
                    }
                }

                // Compose API
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    SectionHeader(stringResource(Res.string.api_compose_title))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        ApiRow("rememberValidatedField", stringResource(Res.string.api_remember_field_desc))
                        ApiRow("rememberValidatedStringForm", stringResource(Res.string.api_remember_form_desc))
                        ApiRow("ComposeValidatedField<T>", stringResource(Res.string.api_compose_field_desc))
                        ApiRow("ComposeValidatedStringForm", stringResource(Res.string.api_compose_form_desc))
                        ApiRow("ValidationSupportingText", stringResource(Res.string.api_supporting_text_desc))
                        ApiRow("ValidatedOutlinedTextField", stringResource(Res.string.api_validated_tf_desc))
                        ApiRow("Modifier.validation()", stringResource(Res.string.api_modifier_validation_desc))
                    }
                }
            }
        }
        Spacer(Modifier.height(72.dp))
    }
}
