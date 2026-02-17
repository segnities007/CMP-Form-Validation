# ADR-0001: Validation Core Shape

- Status: Accepted (initial)
- Date: 2026-02-17

## Context

Compose Multiplatform向けに、UI依存なしで再利用可能なForm Validationコアを先に確立したい。  
各プラットフォームで同じロジックを使うため、`commonMain` 主体の設計が必要。

## Decision

以下を最小スコープとして採用する。

1. コアは `commonMain` に実装する
2. バリデーションは「入力値 -> 0個以上のエラー」の純粋関数モデルにする
3. ルールは合成可能にする（複数ルールを順に適用）
4. 戻り値は真偽値ではなく、エラー情報を含む型を返す
5. Compose向けの状態管理ヘルパーはコアと分離し、後から追加する

## Minimal API Direction (draft)

```kotlin
interface Rule<T> {
    fun validate(value: T): ValidationError?
}

data class ValidationError(
    val code: String,
    val message: String
)

data class ValidationResult(
    val isValid: Boolean,
    val errors: List<ValidationError>
)
```

## Consequences

- 長所:
  - UI層から独立してテストしやすい
  - Kotlin Multiplatformで再利用しやすい
  - エラーコードを起点に将来i18nしやすい
- 短所:
  - 初期段階ではAPIが少なく、利用側の補助コードが必要
  - 早期にAPI固定すると拡張時の変更コストが出る

## Follow-up

- `FieldState` / `FormState` の責務分離を次ADRで定義する
- エラーの `message` を持つか、`code` のみで運用するかを確定する
- 非同期バリデーション（例: サーバー照合）対応方針を決める
