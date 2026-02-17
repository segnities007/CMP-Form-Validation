# Docs Index

このディレクトリは、Form Validationライブラリの議論・決定事項を残すための場所です。

## ADR (Architecture Decision Record)

- `docs/adr/0001-validation-core-shape.md`
  - バリデーションコアの責務と最小API方針
- `docs/adr/0002-react-inspired-design-principles.md`
  - React主要ライブラリ調査を踏まえたv1設計原則
- `docs/adr/0003-form-model-and-catalog-app.md`
  - Formモデルとカタログアプリのv1決定
- `docs/adr/0004-compose-integration-api-tiering.md`
  - Compose統合APIの主戦力/補助レイヤー決定
- `docs/adr/0005-module-split-for-publishing.md`
  - 公開ライブラリとカタログアプリのモジュール分離
- `docs/adr/0006-separate-siteapp-from-catalog.md`
  - カタログアプリとサイトアプリの分離

## Planning / Discussion

- `docs/form-validation-plan.md`
  - 実装ステップ、優先度、未決事項
- `docs/research/react-form-libraries.md`
  - Reactフォームライブラリの設計思想調査
- `docs/compose-validation-usage.md`
  - Composeでの利用パターンと推奨順
- `docs/kdoc-style-guide.md`
  - KDoc作成ルール（公式ガイド準拠）

## Rules

- 仕様や設計に関わる決定はADRとして記録する
- 実装前に未決事項を `docs/form-validation-plan.md` で明確化する
- 破壊的変更を伴う場合は、新しいADRを追加する
- 外部情報を参考にした場合は、参照先URLと参照日を必ず明記する
- 参考元から「採用した点」と「採用しなかった点」を簡潔に残す
