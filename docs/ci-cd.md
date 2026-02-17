# CI/CD and Security

## Overview

This repository uses GitHub Actions for the following workflows:

- CI (lint/test/compile)
- CodeQL (static analysis)
- Dependency Review (dependency risk checks)
- Gitleaks (secret scanning)
- GitHub Pages deployment (`siteApp`)

## Workflows

- `.github/workflows/ci.yml`
  - `validation-core` tests
  - `ktlint` / `detekt`
  - `validation-compose` / `siteApp` compile checks
- `.github/workflows/codeql.yml`
  - Java/Kotlin CodeQL analysis (includes scheduled runs)
- `.github/workflows/dependency-review.yml`
  - Checks dependency additions in PRs by severity
- `.github/workflows/gitleaks.yml`
  - Secret scanning on push/PR
- `.github/workflows/pages.yml`
  - Deploys `siteApp` Wasm artifacts to GitHub Pages

## Security Baseline

- GitHub Actions are pinned to commit SHA (`uses: owner/repo@<sha>`)
- Workflow permissions follow least privilege
- `persist-credentials: false` is enabled
- `timeout-minutes` is set to avoid hanging jobs

## Local Equivalent Checks

```bash
./gradlew --no-daemon :validation-core:jvmTest
./gradlew --no-daemon ktlintCheckAll detektAll
./gradlew --no-daemon :validation-compose:compileKotlinJvm
./gradlew --no-daemon :siteApp:compileKotlinJs :siteApp:compileKotlinWasmJs
```

## Update Policy

- Action updates are merged via Dependabot PRs
- Because actions are SHA-pinned, update `uses:` SHAs when upgrading
- Require all CI checks to pass before merge
