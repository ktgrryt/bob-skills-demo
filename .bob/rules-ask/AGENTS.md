# Askモード固有ルール

## ドキュメント構造

### プロジェクト構成
- `src/main/java/com/demo/` - アプリケーションコード
  - `model/` - JPAエンティティ
  - `repository/` - データアクセス層（@Transactional使用）
  - `service/` - ビジネスロジック層
  - `rest/` - JAX-RS RESTエンドポイント
  - `util/` - ユーティリティクラス（DatabaseUtil等）

### 設定ファイル
- `src/main/liberty/config/server.xml` - Liberty設定（データソース、ポート等）
- `src/main/resources/META-INF/persistence.xml` - JPA設定（persistence-unit: userPU）
- `src/main/resources/META-INF/microprofile-config.properties` - MicroProfile設定

### 非自明な構造
- データソースJNDI名は`jdbc/userDS`（server.xmlとpersistence.xmlで一致必須）
- コンテキストルートは`/bob-skills`（server.xmlで設定）
- REST APIベースパスは`/api`（RestApplication.javaで設定）
- 完全なエンドポイントURL: `http://localhost:9080/bob-skills/api/users`

## 検証用の問題
このプロジェクトには意図的に問題が含まれており、起動しない設計になっています。