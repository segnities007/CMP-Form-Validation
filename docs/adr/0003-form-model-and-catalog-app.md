# ADR-0003: Form Model and Catalog App

- Status: Accepted
- Date: 2026-02-17

## Context

In v1, we need to standardize not only individual rules but also full form validation flow.  
We also need to keep a catalog app that continuously validates implemented behavior and prevents drift between design and runtime behavior.

## Decision

Adopt the following as v1 scope:

1. Add `ValidatedField<T>` to manage `value/touched/dirty/submitted/result`
2. Support trigger switching with `ValidationTrigger`
   - `OnChange`
   - `OnBlur`
   - `OnSubmitThenChange`
3. Add `ValidatedStringForm` to manage multiple fields and cross-field rules
4. Provide `fieldsMatchRule` as a minimal cross-field validation use case
5. Keep `App()` as the catalog app and verify:
   - Rule Catalog
   - Sign-up Form (post-submit revalidation + password match check)

## Consequences

- Pros:
  - Standardizes practical form flow needed in real use
  - Library changes can be quickly verified in catalog screens
  - Cross-field validation is covered in v1
- Cons:
  - v1 is limited to a string-keyed form model
  - Typed form model requires a follow-up phase

## Follow-up

- Evaluate typed form model introduction (reduce `Map` dependency)
- Define `submit` / `revalidate` behavior when async rules are introduced
