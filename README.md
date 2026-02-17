<div align="center">
  <h1>CMP Form Validation</h1>
  <p>A Kotlin Multiplatform form validation library for Compose Multiplatform.</p>
</div>

[![Kotlin](https://img.shields.io/badge/kotlin-2.3.0-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/compose%20multiplatform-1.10.0-4285F4.svg)](https://www.jetbrains.com/compose-multiplatform/)
[![Maven](https://img.shields.io/badge/maven-not%20published-lightgrey.svg)](#installation)
[![GitHub License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/status-early%20stage-orange.svg)](#status)

CMP Form Validation is a KMP library to share form-validation logic across Android, iOS, Desktop, and Web while keeping the validation core UI-agnostic and testable.

## Status

- Early stage (library implementation in progress)
- API is not stable yet
- Maven publishing has not started yet

## Why

- Reuse the same validation rules across all KMP targets
- Keep business validation independent from UI framework details
- Provide Compose-friendly primitives for field/form state handling

## Modules

- `validation-core/`: publishable KMP core (rules, validator, form model)
- `validation-compose/`: publishable Compose integration APIs
- `composeApp/`: catalog app for manual validation checks
- `siteApp/`: docs website app built with Compose Multiplatform Web
- `iosApp/`: iOS entry app for running the catalog

Only `validation-core` and `validation-compose` are intended for OSS artifact publishing.

## Current API Example

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

## Compose Integration Strategy

1. Primary: `rememberValidatedField` + native `OutlinedTextField` / `TextField`
2. Supplementary: `Modifier.validation(field)` for blur-trigger integration
3. Supplementary: `ValidatedOutlinedTextField` for compact UI assembly
4. Core-only: `ValidatedField` / `ValidatedStringForm` for non-UI integration

## Targets

- Android
- iOS
- Desktop (JVM)
- Web (JS / Wasm)

## Installation

Not published to Maven Central yet.

Use this repository as a local source dependency until publishing starts.

## Run Locally

```bash
# validation-core tests
./gradlew :validation-core:jvmTest

# validation-compose compile check
./gradlew :validation-compose:compileKotlinJvm

# Android catalog app
./gradlew :composeApp:assembleDebug

# Desktop catalog app
./gradlew :composeApp:run

# Catalog web app (Wasm / JS)
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
./gradlew :composeApp:jsBrowserDevelopmentRun

# Docs website (Wasm / JS)
./gradlew :siteApp:wasmJsBrowserDevelopmentRun
./gradlew :siteApp:jsBrowserDevelopmentRun
```

The catalog `App()` currently demonstrates:

- Rule Catalog: behavior of individual rules
- Patterns: Primary / Modifier / Wrapper usage styles
- Sign-up Form: submit validation + revalidation flow

## Documentation

Design decisions and logs are in `docs/`.

- Index: `docs/README.md`
- ADR-0001: `docs/adr/0001-validation-core-shape.md`
- ADR-0002: `docs/adr/0002-react-inspired-design-principles.md`
- ADR-0003: `docs/adr/0003-form-model-and-catalog-app.md`
- ADR-0004: `docs/adr/0004-compose-integration-api-tiering.md`
- ADR-0005: `docs/adr/0005-module-split-for-publishing.md`
- Plan: `docs/form-validation-plan.md`
- Research: `docs/research/react-form-libraries.md`
- Usage guide: `docs/compose-validation-usage.md`
- KDoc guide: `docs/kdoc-style-guide.md`

## Roadmap

- Prepare publishing setup (group/artifact/versioning)
- Add typed form model on top of the current string-keyed immutable form core
- Add async validation model (post-v1)

## Contributing

Issues and pull requests are welcome.

For design-impacting changes, add or update relevant docs under `docs/` before implementation.

## License

Licensed under the Apache License 2.0.

- License text: `LICENSE`
- Attribution notice: `NOTICE`
