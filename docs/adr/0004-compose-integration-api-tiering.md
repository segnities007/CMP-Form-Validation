# ADR-0004: Compose Integration API Tiering

- Status: Accepted
- Date: 2026-02-17

## Context

Users want to add validation easily while keeping existing TextField code.  
At the same time, explicit state modeling is more maintainable in Compose.

## Decision

Tier the Compose-facing APIs as follows:

1. Primary:
- `rememberValidatedField` + native `OutlinedTextField` / `TextField`

2. Supplementary:
- `Modifier.validation(field)`
- `ValidatedOutlinedTextField(field, ...)`

3. Core-only:
- `ValidatedField`
- `ValidatedStringForm`

## Rationale

- Primary approach makes state transitions and validation timing easy to trace
- Modifier approach is useful as a low-adoption-cost supplement
- Wrapper approach reduces boilerplate but introduces abstraction cost

## Consequences

- Documentation and catalog prioritize the Primary approach
- Supplementary approaches remain officially supported for use-case-based selection
