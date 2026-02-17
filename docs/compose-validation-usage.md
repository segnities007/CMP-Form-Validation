# Compose Validation Usage

Last updated: 2026-02-17

## API Tiering

This library supports multiple integration patterns. Recommended order:

1. Primary: `rememberValidatedField` + native `OutlinedTextField` / `TextField`
2. Supplementary: `Modifier.validation(field)`
3. Supplementary: `ValidatedOutlinedTextField`
4. Core-only: `ValidatedField` / `ValidatedStringForm`

## 1) Primary (Recommended)

```kotlin
import kotlinx.collections.immutable.persistentListOf

val emailField = rememberValidatedField(
    rules = persistentListOf(required(), email()),
)

OutlinedTextField(
    value = emailField.value,
    onValueChange = emailField::onValueChange,
    isError = emailField.showErrors && !emailField.result.isValid,
    supportingText = { ValidationSupportingText(emailField) },
)
```

Characteristics:

- Explicit state flow in Compose
- Easy to debug
- Keeps native TextField usage patterns

## 2) Modifier-based (Supplementary)

```kotlin
OutlinedTextField(
    value = passwordField.value,
    onValueChange = passwordField::onValueChange,
    modifier = Modifier.validation(passwordField),
)
```

When to use:

- When you want to add blur-based validation without heavily changing existing field structure

Notes:

- Use `Modifier` as a supplementary approach

## 3) Wrapper-based (Supplementary)

```kotlin
ValidatedOutlinedTextField(
    field = passwordField,
    label = { Text("Password") },
)
```

When to use:

- When you want shorter UI code

Notes:

- Wrapper APIs may not always expose every parameter from native TextField components

## 4) Core-only

```kotlin
val coreField = ValidatedField(
    initialValue = "",
    validator = validatorOf(required(), minLength(8)),
)
```

When to use:

- When building non-Compose integration layers
- When using validation models as internal foundation logic
