# Planモード固有ルール

## アーキテクチャの制約

### レイヤー構造
```
REST Layer (UserResource)
    ↓
Service Layer (UserService) - バリデーション実行
    ↓
Repository Layer (UserRepository) - @Transactional境界
    ↓
JPA/Database
```

### 非自明な設計決定
- トランザクション境界はRepositoryレイヤー（Serviceではない）
- `@Transactional`はRepositoryの各メソッドに個別に付与
- CDI Beanは`@ApplicationScoped`を使用（`@RequestScoped`ではない）
- フィールドインジェクション使用（コンストラクタインジェクションではない）

### データベース初期化
- `DatabaseUtil`が`@Startup`で起動時に実行
- JDBCで直接テーブル作成（JPA schema generationは無効）
- Derby in-memoryデータベース使用

### REST API設計
- エラーハンドリングはtry-catchでResponse.statusを返す
- 例外マッパーは使用しない設計
- `@Path`、`@Produces`、`@Consumes`はクラスレベルで宣言

## 検証用の問題
このプロジェクトには意図的に複数の問題が含まれており、起動しない設計になっています。
問題の発見と修正がこのプロジェクトの目的です。