# validation-core

Core publishable validation library.

## Scope

- Validation primitives: `Rule`, `Validator`, `ValidationResult`, `ValidationError`
- Built-in rules: `required`, `minLength`, `maxLength`, `pattern`, `email`
- Form model: `ValidatedField`, `ValidatedStringForm`, `FormRule`

## Notes

- No Compose UI dependencies.
- Uses immutable collections for compose stability.
