# ADR Index

Architecture Decision Records (ADRs) document stable, project-level decisions.

## Active ADRs

- [0001-validation-core-shape.md](0001-validation-core-shape.md): Core validation design must remain UI-agnostic and centered in `commonMain`.
- [0003-form-model-and-catalog-app.md](0003-form-model-and-catalog-app.md): v1 form model scope and catalog-driven verification strategy.
- [0004-compose-integration-api-tiering.md](0004-compose-integration-api-tiering.md): Compose API tiering (Primary/Supplementary/Core-only).
- [0005-module-split-for-publishing.md](0005-module-split-for-publishing.md): Publishable library modules separated from app/demo code.
- [0006-separate-siteapp-from-catalog.md](0006-separate-siteapp-from-catalog.md): Website app ownership/deployment separated from catalog app.

## ADR Writing Rule

Add a new ADR only when the decision changes architecture, public API direction, module boundaries, or release strategy.
