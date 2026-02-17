# ADR-0001: Validation Core Shape

- Status: Accepted (initial)
- Date: 2026-02-17

## Context

For Compose Multiplatform, we need a reusable form validation core that is independent from UI concerns.  
To reuse the same logic across platforms, the design should be centered on `commonMain`.

## Decision

Adopt the following as the minimum scope:

1. Implement the core in `commonMain`
2. Use a pure-function model: input value -> zero or more errors
3. Make rules composable (apply multiple rules in order)
4. Return rich result types with error details, not simple booleans
5. Keep Compose state helpers separate from core and add them later

## Consequences

- Pros:
  - Easy to test independently from UI layers
  - Easy reuse across Kotlin Multiplatform targets
  - Error-code-first approach helps future i18n
- Cons:
  - Initial API surface is small, so consumers may need helper code
  - Early API stabilization can increase future extension cost

## Follow-up

- Define responsibility boundaries between `FieldState` / `FormState` in a later ADR
- Finalize whether to keep `message` in errors or use `code` only
- Define policy for async validation (for example server-side checks)
