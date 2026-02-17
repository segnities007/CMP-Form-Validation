# Compose Validation Usage

最終更新: 2026-02-17

## API Tiering

このライブラリは複数の導入パターンを提供するが、推奨順は以下。

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

特徴:

- Composeの状態が明示的
- デバッグしやすい
- 既存TextField利用を維持できる

## 2) Modifier-based (Supplementary)

```kotlin
OutlinedTextField(
    value = passwordField.value,
    onValueChange = passwordField::onValueChange,
    modifier = Modifier.validation(passwordField),
)
```

用途:

- 既存フィールド構成を大きく崩さずblur検証を追加したい場合

注意:

- `Modifier` は補助用途として使う

## 3) Wrapper-based (Supplementary)

```kotlin
ValidatedOutlinedTextField(
    field = passwordField,
    label = { Text("Password") },
)
```

用途:

- 画面実装を短くしたい場合

注意:

- 標準TextFieldの全パラメータを常に追随できるとは限らない

## 4) Core-only

```kotlin
val coreField = ValidatedField(
    initialValue = "",
    validator = validatorOf(required(), minLength(8)),
)
```

用途:

- Compose以外の統合レイヤーを作る場合
- ライブラリ内部の基盤ロジックとして使う場合
