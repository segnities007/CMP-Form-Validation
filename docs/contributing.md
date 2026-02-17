# Contributing

## Issue

Please create issues using the provided templates.

- Bug: include reproduction steps, expected behavior, and actual behavior.
- Feature: include problem statement, proposal, and definition of done.

For security issues, use Security Advisories instead of public issues.
See [SECURITY.md](../SECURITY.md) for details.

## Pull Request

When opening a PR, follow [`.github/PULL_REQUEST_TEMPLATE.md`](../.github/PULL_REQUEST_TEMPLATE.md).

## Git Hooks (Recommended)

Install local hooks once:

```bash
./scripts/setup-git-hooks.sh
```

Installed hooks:

- `pre-commit`: conflict markers, common secret patterns, key/certificate file extensions, and `ktlint` for affected modules
- `pre-push`: `gitleaks` (if installed) + Gradle quality/test/compile checks

Temporary bypass (not recommended):

```bash
SKIP_GIT_HOOKS=1 git push
```

Minimum checks:

- `./gradlew --no-daemon ktlintCheckAll detektAll`
- Run tests/compile checks for affected modules
- Update `docs/` when APIs or behavior change

## Design Changes

If the change includes architectural decisions, add or update an ADR.

- Location: [`docs/adr/`](adr/)
- Breaking changes must include rationale and migration notes

## Commit and PR Scope

- Keep PRs small and reviewable.
- Do not mix unrelated changes in a single PR.
