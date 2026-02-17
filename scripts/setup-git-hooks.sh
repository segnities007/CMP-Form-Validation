#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

git config core.hooksPath .githooks
chmod +x .githooks/pre-commit .githooks/pre-push

echo "Git hooks configured."
echo "core.hooksPath=$(git config --get core.hooksPath)"
echo "Installed hooks:"
ls -1 .githooks
