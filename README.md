# cmpformvalidation

A Kotlin Multiplatform form validation library for Compose Multiplatform.

## Status

- Early stage (template -> library implementation in progress)
- API is not stable yet
- Maven publishing is not started yet

## Why

- Reuse the same validation logic across Android, iOS, Desktop, and Web
- Keep validation engine UI-agnostic and highly testable
- Provide Compose-friendly APIs for state and error handling

## Planned Features

- Built-in rules: `required`, `minLength`, `maxLength`, `pattern`, `email`
- Rule composition (chain, short-circuit, custom predicates)
- Field-level and form-level validation
- Typed validation result and error model
- Kotlin Multiplatform common tests

## Current API (v1)

```kotlin
val field = rememberValidatedField(
    initialValue = "",
    trigger = ValidationTrigger.OnSubmitThenChange,
    rules = persistentListOf(required(), minLength(8)),
)

field.onValueChange("abc")
field.submit() // invalid
field.onValueChange("abc12345") // revalidated after submit
```

## Compose Integration (Recommended Order)

1. Primary: `rememberValidatedField` + native `OutlinedTextField` / `TextField`
2. Supplementary: `Modifier.validation(field)` for blur-trigger integration
3. Supplementary: `ValidatedOutlinedTextField` for compact UI construction
4. Core-only: `ValidatedField` / `ValidatedStringForm` for non-UI or framework-level integration

The library provides all methods above, but documentation and catalog prioritize the primary approach.

## Targets

- Android
- iOS
- Desktop (JVM)
- Web (JS / Wasm)

## Installation

Not published to Maven Central yet.

Until publishing starts, use this repository as local source dependency in your project.

## Quick Start (Current Project Commands)

```bash
# validation-core tests
./gradlew :validation-core:jvmTest

# validation-compose compile check
./gradlew :validation-compose:compileKotlinJvm

# Android
./gradlew :composeApp:assembleDebug

# Desktop
./gradlew :composeApp:run

# Web (Wasm)
./gradlew :composeApp:wasmJsBrowserDevelopmentRun

# Web (JS)
./gradlew :composeApp:jsBrowserDevelopmentRun

# Website (Wasm, dedicated module)
./gradlew :siteApp:wasmJsBrowserDevelopmentRun

# Website (JS, dedicated module)
./gradlew :siteApp:jsBrowserDevelopmentRun
```

`App()` はカタログアプリとして実装されており、以下を確認できます。

- Rule Catalog: 単体ルールの挙動確認
- Patterns: Primary / Modifier / Wrapper の使い分け
- Sign-up Form: submit時検証 + 再入力時再検証フロー

## Website

The website is implemented with Compose Multiplatform in a dedicated module.

- Module: `siteApp/`
- Entry point: `siteApp/src/webMain/kotlin/com/segnities007/cmp_form_validation/main.kt`
- Site composable: `siteApp/src/commonMain/kotlin/com/segnities007/cmp_form_validation/site/DocsSiteApp.kt`

## Project Structure

- `validation-core/`: publishable KMP core library (rules, validator, form model)
- `validation-compose/`: publishable Compose integration library (remember/Modifier/wrapper APIs)
- `composeApp/`: non-publish catalog app for manual verification
- `siteApp/`: non-publish website app (CMP web target)
- `iosApp/`: iOS app entry (SwiftUI) for catalog execution

Only `validation-core` and `validation-compose` are intended for OSS artifact publishing.

## Design Docs

Design decisions and discussion logs are kept in `docs/`.

- Decision log index: `docs/README.md`
- Initial architecture decision: `docs/adr/0001-validation-core-shape.md`
- React-inspired principles: `docs/adr/0002-react-inspired-design-principles.md`
- Form model decision: `docs/adr/0003-form-model-and-catalog-app.md`
- Compose API tiering decision: `docs/adr/0004-compose-integration-api-tiering.md`
- Module split for publishing: `docs/adr/0005-module-split-for-publishing.md`
- Backlog and open questions: `docs/form-validation-plan.md`
- Research notes: `docs/research/react-form-libraries.md`
- Usage guide: `docs/compose-validation-usage.md`
- KDoc style guide: `docs/kdoc-style-guide.md`

## Research Attribution

This project documents external references used for design decisions.

- Source links are recorded in each research document.
- Adopted principles are written in ADRs.
- We do not copy implementation code from other libraries.

## Roadmap

- Prepare publishing setup (group/artifact/versioning)
- Add typed form model on top of current string-keyed immutable form core
- Add async validation model (post-v1)

## Contributing

Issues and pull requests are welcome.

For design-impacting changes, please add/update a doc under `docs/` first, then implement.

## License

Licensed under the Apache License 2.0.

- License text: `LICENSE`
- Attribution notice: `NOTICE`
