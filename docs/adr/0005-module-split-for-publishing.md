# ADR-0005: Split Publishable Modules from Catalog App

- Status: Accepted
- Date: 2026-02-17

## Context

When the repository was a single `composeApp` module, publishable library code and catalog/demo code were mixed.
That makes artifact publishing risky because non-library code can leak into released binaries.

## Decision

Split modules by responsibility:

1. `validation-core`
- Publishable KMP core logic only.

2. `validation-compose`
- Publishable Compose integration layer.
- Depends on `validation-core`.

3. `composeApp`
- Non-publish catalog app used for manual verification.
- Depends on both publishable modules.

## Consequences

- Publishing scope is explicit and safer.
- Public API surface can be managed per library module.
- Demo/catalog iteration no longer pollutes publishable artifacts.
