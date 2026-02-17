package com.segnities007.cmp_form_validation.site.pages

object CodeSamples {
    // ── HomePage ─────────────────────────────────────────────────────────────

    val QUICK_START =
        """
        val email = rememberValidatedField(
            rules = persistentListOf(required(), email())
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = email::onValueChange,
            label = { Text("Email") },
            isError = email.showErrors && !email.result.isValid,
            supportingText = { ValidationSupportingText(email) },
            modifier = Modifier.validation(email)
        )
        """.trimIndent()

    // ── DocsPage ─────────────────────────────────────────────────────────────

    val ADD_DEPS =
        """
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

    val CREATE_FIELD =
        """
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

    val BIND_UI =
        """
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

    val TRIGGER =
        """
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

    val STRATEGY =
        """
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

    val FORM =
        """
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

    val CUSTOM_RULE =
        """
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

    // ── ExamplesPage ─────────────────────────────────────────────────────────

    val EMAIL =
        """
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

    val LOGIN =
        """
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

    val SIGNUP =
        """
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

    val CUSTOM =
        """
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

    val MODIFIER =
        """
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

    val WRAPPER =
        """
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
}
