# ADR-0006: Separate `siteApp` from Catalog App

- Status: Accepted
- Date: 2026-02-17

## Context

The repository had two different UX products:

1. Catalog app for internal validation behavior checks
2. Public-facing website experience

Keeping both in one app module made ownership and deployment boundaries unclear.

## Decision

Create a dedicated `siteApp` module for the website and keep `composeApp` focused on catalog usage.

- `composeApp`: catalog app
- `siteApp`: CMP website app (web targets)

## Consequences

- Clear separation of responsibilities
- Cleaner deployment path for website
- Lower chance of accidental coupling between catalog and website UI concerns
