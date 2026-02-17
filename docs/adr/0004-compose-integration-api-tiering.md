# ADR-0004: Compose Integration API Tiering

- Status: Accepted
- Date: 2026-02-17

## Context

利用者は「既存TextFieldコードを保ちながら簡単にValidationを追加したい」。  
一方で、Composeの思想としては状態を明示する設計が保守しやすい。

## Decision

Compose向けAPIは階層化する。

1. Primary:
- `rememberValidatedField` + native `OutlinedTextField` / `TextField`

2. Supplementary:
- `Modifier.validation(field)`
- `ValidatedOutlinedTextField(field, ...)`

3. Core-only:
- `ValidatedField`
- `ValidatedStringForm`

## Rationale

- Primary方式は状態遷移と検証タイミングが追跡しやすい
- Modifier方式は導入コストを下げる補助として有効
- Wrapper方式は記述量削減に有効だが抽象化コストがある

## Consequences

- ドキュメントとカタログはPrimary方式を主戦力として説明する
- 補助方式も正式サポートし、用途別に使い分け可能にする
