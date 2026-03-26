# アドバンスモード固有ルール

## 非自明なコーディングパターン

### データアクセス層
- `UserRepository`では`@Transactional`を必ずメソッドレベルで使用
- `EntityManager`は`@PersistenceContext(unitName = "userPU")`で注入
- トランザクション境界はRepositoryレイヤーで管理（Serviceレイヤーではない）

### CDI Bean管理
- すべてのビジネスロジッククラスは`@ApplicationScoped`を使用
- `@Inject`でDI（コンストラクタインジェクションではなくフィールドインジェクション）

### REST エンドポイント
- `@Path`、`@Produces`、`@Consumes`は必ずクラスレベルで宣言
- エラーハンドリングはtry-catchでResponse.statusを返す（例外マッパーは使用しない）

### データベース初期化
- `DatabaseUtil`は`@Singleton`と`@Startup`で起動時に自動実行
- JDBCで直接テーブル作成とデータ投入を行う（JPA schema generationは使用しない）

## 拡張機能
- MCPツールやブラウザツールへのアクセスが可能
- 外部リソースの参照や検証が可能