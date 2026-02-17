# ADR-0003: Form Model and Catalog App

- Status: Accepted
- Date: 2026-02-17

## Context

v1では、ルール単体だけでなく「フォームとしての検証フロー」を標準化する必要がある。  
また、実装済み機能を常に確認できるカタログアプリを維持し、設計と挙動の乖離を防ぎたい。

## Decision

以下をv1スコープとして採用する。

1. `ValidatedField<T>` を追加し、`value/touched/dirty/submitted/result` を管理する
2. 検証トリガを `ValidationTrigger` で切り替える
   - `OnChange`
   - `OnBlur`
   - `OnSubmitThenChange`
3. `ValidatedStringForm` を追加し、複数フィールドとクロスフィールドルールを扱う
4. クロスフィールド検証の最小ユースケースとして `fieldsMatchRule` を提供する
5. `App()` をカタログアプリとして維持し、以下を確認可能にする
   - Rule Catalog
   - Sign-up Form (submit後再検証 + password一致検証)

## Consequences

- 長所:
  - 実運用で必要なフォームフローを共通化できる
  - ライブラリ変更をカタログ画面で即確認できる
  - クロスフィールド検証までv1でカバーできる
- 短所:
  - v1は string-keyed フォームモデルに限定される
  - 型付きフォームモデルは次フェーズで検討が必要

## Follow-up

- 型付きフォームモデルの導入検討（`Map` 依存の削減）
- 非同期ルールを導入する際の `submit` / `revalidate` 振る舞い設計
