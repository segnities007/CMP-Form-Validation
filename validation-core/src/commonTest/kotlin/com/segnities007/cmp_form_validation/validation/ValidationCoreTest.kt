package com.segnities007.cmp_form_validation.validation

import kotlinx.collections.immutable.persistentMapOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidationCoreTest {
    @Test
    fun requiredRuleFailsOnBlank() {
        val validator = validatorOf(required())
        val result = validator.validate("   ")

        assertFalse(result.isValid)
        assertEquals(ErrorCode.REQUIRED, result.errors.first().code)
    }

    @Test
    fun minAndMaxLengthRulesWork() {
        val validator = validatorOf(minLength(3), maxLength(5))

        assertFalse(validator.validate("ab").isValid)
        assertTrue(validator.validate("abcd").isValid)
        assertFalse(validator.validate("abcdef").isValid)
    }

    @Test
    fun emailRuleAcceptsSimpleAddress() {
        val validator = validatorOf(email())
        assertTrue(validator.validate("dev@example.com").isValid)
        assertFalse(validator.validate("dev@example").isValid)
    }

    @Test
    fun patternRuleUsesRegex() {
        val validator = validatorOf(pattern(Regex("^\\d{3}-\\d{4}$")))
        assertTrue(validator.validate("123-4567").isValid)
        assertFalse(validator.validate("1234567").isValid)
    }

    @Test
    fun customRuleCanBeComposed() {
        val containsNumber =
            Rule<String> { value ->
                if (value.any(Char::isDigit)) {
                    null
                } else {
                    ValidationError("missing_number", "Number required.")
                }
            }

        val validator = validatorOf(required(), minLength(4), containsNumber)
        val result = validator.validate("abcd")

        assertFalse(result.isValid)
        assertEquals("missing_number", result.errors.last().code)
    }

    @Test
    fun firstErrorStrategyStopsAfterFirstFailure() {
        val validator =
            validatorOf(
                ValidationStrategy.FirstError,
                required(),
                minLength(5),
            )

        val result = validator.validate("")

        assertFalse(result.isValid)
        assertEquals(1, result.errors.size)
        assertEquals(ErrorCode.REQUIRED, result.errors.first().code)
    }

    @Test
    fun fieldOnSubmitThenChangeValidatesAfterSubmit() {
        val field =
            ValidatedField(
                initialValue = "",
                validator = validatorOf(required(), minLength(3)),
                trigger = ValidationTrigger.OnSubmitThenChange,
            )

        field.onValueChange("ab")
        assertTrue(field.result.isValid)

        field.submit()
        assertFalse(field.result.isValid)

        field.onValueChange("abcd")
        assertTrue(field.result.isValid)
    }

    @Test
    fun formRuleDetectsMismatchedFields() {
        val passwordField =
            ValidatedField(
                initialValue = "",
                validator = validatorOf(required(), minLength(8)),
                trigger = ValidationTrigger.OnSubmitThenChange,
            )
        val confirmField =
            ValidatedField(
                initialValue = "",
                validator = validatorOf(required()),
                trigger = ValidationTrigger.OnSubmitThenChange,
            )

        passwordField.onValueChange("password1")
        confirmField.onValueChange("password2")

        val form =
            ValidatedStringForm(
                fields =
                    persistentMapOf(
                        "password" to passwordField,
                        "confirmPassword" to confirmField,
                    ),
                formRules =
                    listOf(
                        fieldsMatchRule("confirmPassword", "password", code = "password_mismatch"),
                    ),
            )

        val snapshot = form.submit()

        assertFalse(snapshot.isValid)
        assertEquals("password_mismatch", snapshot.formErrors.first().code)
    }

    @Test
    fun onBlurTriggerShowsErrorsOnlyAfterBlur() {
        val field =
            ValidatedField(
                initialValue = "",
                validator = validatorOf(required()),
                trigger = ValidationTrigger.OnBlur,
            )

        field.onValueChange("")
        assertFalse(field.showErrors)

        field.onBlur()
        assertTrue(field.showErrors)
        assertFalse(field.result.isValid)
    }
}
