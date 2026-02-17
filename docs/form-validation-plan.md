# Form Validation Plan

最終更新: 2026-02-17

## Goal

Compose Multiplatformで使える、軽量で型安全なForm Validationライブラリを構築する。

## Phase Plan

## Phase 1: Core Domain (commonMain)
Status: Completed

- `Rule<T>` の定義
- `ValidationError` / `ValidationResult` の定義
- 基本ルール実装:
  - `required`
  - `minLength`
  - `maxLength`
  - `pattern`
  - `email`（正規表現は過剰厳密にしない）
- ルール合成ユーティリティ
- 実行戦略: `CollectAll` / `FirstError`

Done条件:

- 共通テストで主要ルールを検証できる
- APIがUI依存を持たない

## Phase 2: Form Model
Status: Completed (String form model)

- フィールド単位の検証モデル
- フォーム全体の検証モデル
- `touched` / `dirty` の扱い定義
- クロスフィールド検証 (`fieldsMatchRule`)

Done条件:

- 複数フィールドのクロスバリデーションが可能
- 利用側コードが簡潔になる

## Phase 3: Compose Integration
Status: Completed (catalog level)

- 入力変更時検証 / submit時検証の戦略化
- サンプル画面（Android/Desktop）
- カタログアプリ (`Rule Catalog`, `Sign-up Form`) で確認可能
- API階層化（Primary/Supplementary/Core-only）を定義

Done条件:

- 最小のサンプルフォームで利用手順を示せる

## Phase 4: Publishing & OSS
Status: Pending

- Artifact座標・versioning方針
- Maven Central公開準備
- CONTRIBUTING / issue template整備
- モジュール分離（publishable modulesとcatalog appの境界明確化）: 完了

Done条件:

- 外部プロジェクトから依存追加して利用できる

## Open Questions (to decide together)

1. Error message strategy
- A案: `code` のみ保持し、表示文言は呼び出し側で解決
- B案: `code + defaultMessage` を保持（初期はB推奨）
 - 決定: B案（ADR-0002）

2. Validation timing
- A案: 入力のたびに検証
- B案: blur時
- C案: submit時 + 変更時再検証（初期はC推奨）
 - 決定: C案（ADR-0002）

3. Async validation scope
- A案: v1では対象外
- B案: `suspend Rule` を初期から導入（初期はA推奨）
 - 決定: A案（ADR-0002）

4. API style
- A案: 関数ベース（`minLength(8)`）
- B案: DSLベース（`rules { ... }`）
- 初期はAで開始し、DSLは後方互換で追加
 - 決定: 初期A（ADR-0002）

## Next discussion agenda

- 型付きフォームモデル導入可否（現行のstring-keyed immutable modelからの拡張）
- async validation の仕様設計（キャンセル戦略・競合解決）
- Maven Central公開に向けたartifact設計
