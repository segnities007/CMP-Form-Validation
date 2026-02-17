# ADR-0002: React-Inspired Design Principles

- Status: Accepted
- Date: 2026-02-17
- Related: `docs/research/react-form-libraries.md`

## Context

Reactの主要フォームライブラリは、それぞれ異なる強みを持つ:

- React Hook Form: 高パフォーマンスと最小再レンダリング
- Formik: 分かりやすいフォーム管理体験
- Final Form: サブスクリプション駆動の状態管理
- TanStack Form: 型安全と拡張性

cmpformvalidationでも、これらの長所をKMP向けに再構成する。

## Decision

v1の設計原則を以下に固定する。

1. バリデーションコアはUI非依存
2. エラーは `code + defaultMessage` を保持
3. ルールAPIは関数ベース (`required()`, `minLength()`) で開始
4. v1の検証タイミングは「submit時 + 以後再入力時再検証」
5. 非同期バリデーションはv1では対象外（v2以降）

## Consequences

- 長所:
  - 早期に使える最小機能を安定実装できる
  - マルチプラットフォームで同一のコアを共有できる
  - 将来のDSL/async追加余地を維持できる
- 短所:
  - v1時点では複雑な業務要件（非同期照合など）に直接は対応しない
  - 一部ユースケースでアプリ側補助コードが必要

## Follow-up

- Compose向け購読モデル（部分更新）を次ADRで定義
- async rule仕様（cancellation/merge strategy）を別ADRで定義
