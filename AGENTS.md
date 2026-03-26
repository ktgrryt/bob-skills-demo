# AGENTS.md

このファイルは、このリポジトリでコードを扱う際のエージェント向けガイダンスを提供します。

## プロジェクト概要
- **スタック**: Open Liberty (Jakarta EE 10.0 + MicroProfile 7.0)
- **ビルドツール**: Maven (liberty-maven-plugin)
- **Java**: 21
- **パッケージング**: WAR

## 重要なコマンド
```bash
# 開発モードで起動（ホットリロード有効）
./mvnw liberty:dev        # Linux/Mac
mvnw liberty:dev          # Windows

# ビルド
./mvnw clean package

# Dockerイメージビルド
docker build -t bob-skills .
```

## プロジェクト固有の注意点

### データソース設定
- `server.xml`でDerbyデータソースを`jdbc/userDS`として定義
- `persistence.xml`の`jta-data-source`は`jdbc/userDS`を参照する必要がある
- Derbyライブラリは`${shared.resource.dir}/derby`から読み込まれる想定

### JPA設定
- persistence-unit名は`userPU`
- `@PersistenceContext(unitName = "userPU")`で参照
- トランザクションタイプはJTA

### REST API
- ベースパス: `/api` (`@ApplicationPath("/api")`)
- コンテキストルート: `/bob-skills` (server.xmlで設定)
- 完全なURL例: `http://localhost:9080/bob-skills/api/users`

### ポート設定
- HTTP: 9080
- HTTPS: 9443

### トランザクション管理
- `@Transactional`アノテーションはRepositoryレイヤーで使用
- CDI `@ApplicationScoped`でBeanを管理

## 検証用の意図的な問題
このプロジェクトには検証目的で意図的に問題が含まれています。