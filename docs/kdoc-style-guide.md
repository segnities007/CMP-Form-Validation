# KDoc Style Guide

Last updated: 2026-02-17
Referenced on: 2026-02-17

## Sources

- Kotlin official docs (KDoc syntax): https://kotlinlang.org/docs/kotlin-doc.html
- Kotlin coding conventions (documentation comments): https://kotlinlang.org/docs/coding-conventions.html#documentation-comments
- Android Kotlin style guide (KDoc guidance): https://developer.android.com/kotlin/style-guide

## Core Rules We Follow

1. Document public API first
- Add KDoc to public classes, interfaces, functions, and important public properties.
- Avoid documenting private implementation details unless complexity requires it.

2. Start with a short summary sentence
- First sentence explains *what* the API is.
- Additional lines explain *why* it exists and *how* to use it.

3. Prefer behavior over implementation detail
- Describe observable behavior, constraints, and side effects.
- Keep internals out unless they affect callers.

4. Use tags when they add value
- Use `@param`, `@return`, and `@throws` for non-obvious contracts.
- Avoid redundant tags that only restate names.

5. Keep docs close to code and updated with refactors
- API changes must update KDoc in the same change.
- Examples in docs should compile conceptually with current API.

6. Avoid noise and duplication
- Do not write comments that merely repeat signature text.
- Keep wording concise and actionable.

## Applied to This Project

- Validation core APIs (`Rule`, `Validator`, `ValidationResult`, rules) have KDoc.
- Form model APIs (`ValidatedField`, `ValidatedStringForm`, triggers/rules) have KDoc.
- Compose integration APIs (`rememberValidatedField`, `Modifier.validation`, wrappers) have KDoc.
- Catalog screens include short KDoc describing purpose.
