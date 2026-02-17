# React Form Libraries Research

作成日: 2026-02-17
参照日: 2026-02-17

## Scope

Reactで広く使われるフォームライブラリの設計思想を調査し、cmpformvalidationの設計方針に反映する。

調査対象:

- React Hook Form
- Formik
- Final Form
- TanStack Form

## Key Findings

## 1) React Hook Form

- 公式サイトは「performance」「UX」「DX」を主軸にしている
- 無駄な再レンダリング抑制と、制御が少ない構成（uncontrolledベース）の思想が強い
- 検証ロジックをスキーマライブラリと接続しやすいエコシステムを持つ

設計への示唆:

- コアは小さく保ち、UI結合を弱くする
- 状態購読は必要最小限に分離する（全体再計算を避ける）

## 2) Formik

- 「フォーム状態 + バリデーション + エラーメッセージ」を一貫管理する体験を重視
- Render props / hooks両方を提供し、導入しやすさを重視

設計への示唆:

- 初学者向けに分かりやすいAPIを用意する
- フォーム全体モデルを段階的に追加する

## 3) Final Form

- サブスクリプションベースで必要な状態のみ購読する設計
- フレームワーク非依存のコア + UIバインディングの分離が明確

設計への示唆:

- `commonMain` コアをUI非依存に保つ
- Composeバインディングは別レイヤーとして設計する

## 4) TanStack Form

- 型安全性とスケーラブルな状態管理を強く訴求
- 標準スキーマとの連携や、大規模フォーム運用を意識

設計への示唆:

- Kotlinの型システムを活かし、結果型を明確化する
- エラーコード中心でi18n可能なモデルを採用する

## Design Principles for cmpformvalidation

1. Core-first: バリデーションコアは UI 非依存 (`commonMain`) に置く
2. Typed result: `ValidationResult` / `ValidationError` を中心に設計する
3. Composable rules: ルールは合成可能で順序制御しやすくする
4. Progressive API: まず関数ベースAPI、後からDSLを追加する
5. Subscription-friendly UI: Compose統合時は部分更新しやすいAPIにする

## Adoption Notes (Honesty Log)

この調査から実際に採用した点:

- UI非依存コア（Final Formの分離思想に近い）
- 型付き結果モデル（TanStack Formの型安全志向を参考）
- submit後の再検証フロー（実運用でのUX重視）
- 小さなコア + 段階的拡張（RHFの軽量思想を参考）

この調査で採用していない点（現時点）:

- RHFのuncontrolled中心アーキテクチャをそのまま再現はしない
- Formik互換APIの提供は行わない
- 非同期バリデーションはv1では導入しない
- 既存ライブラリの実装コードは移植しない

## Sources

- React Hook Form: https://react-hook-form.com/
- Formik: https://formik.org/
- Final Form philosophy: https://final-form.org/philosophy
- TanStack Form docs: https://tanstack.com/form/latest/docs/overview
