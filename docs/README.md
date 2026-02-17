# Docs Index

This directory is the central place for design decisions, usage guidance, and operational documentation for `cmpformvalidation`.

## Start Here

- [Compose validation usage](compose-validation-usage.md): How to use the library (Primary/Modifier/Wrapper)
- [CI/CD and security](ci-cd.md): GitHub Actions CI/CD and security operations
- [Contributing](contributing.md): Issue/PR workflow and minimum verification checks

## ADR (Architecture Decision Record)

- [ADR index](adr/README.md)

## Scope Note

- Archived internal planning and research notes were removed to keep OSS docs focused on user-facing and contributor-facing content.

## Implementation Map

- `validation-core`: Rules, validators, and form model (UI-agnostic)
- `validation-compose`: Compose state management and UI helpers
- `composeApp`: Catalog app (manual verification)
- `siteApp`: Documentation website (GitHub Pages deployment)

## Documentation Update Rules

- If behavior or API changes, update docs in the same PR.
- Record architecture decisions in ADR files.
- Reflect CI/CD or operational rule changes in `ci-cd.md`.
