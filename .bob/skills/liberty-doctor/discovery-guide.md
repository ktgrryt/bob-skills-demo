# Liberty Doctor Discovery Guide

このファイルは、探索順序と優先順位を揃えるための補助資料である。
診断時はここを参照し、無駄な探索を減らすこと。

## 1. server.xml の候補優先順位

複数の `server.xml` がある場合は、以下の順で優先する。

1. `src/main/liberty/config/server.xml`
2. `config/server.xml`
3. `wlp/usr/servers/*/server.xml`
4. その他の `**/server.xml`

判断材料:

- Maven/Gradle の Liberty プラグイン設定と整合していそうか
- ソース管理対象らしい場所か
- 一時生成物やローカル runtime 配下ではないか
- 同階層に `bootstrap.properties` や `configDropins` があるか

どうしても判断できない場合だけ、短く質問する。

## 2. まず見るべき設定ファイル

### Liberty 設定
- `server.xml`
- `configDropins/defaults/`
- `configDropins/overrides/`
- `bootstrap.properties`
- `jvm.options`
- `server.env`
- `liberty.env`

### ビルド設定
- `pom.xml`
- `mvnw`
- `.mvn/`
- `build.gradle`
- `build.gradle.kts`
- `gradlew`

### ログ
- 最新の `messages.log`
- 対応する `console.log`
- `ffdc/` の有無または件数

## 3. 設定の散らばりで最初に整理する観点

### ネットワーク
- `httpPort`
- `httpsPort`
- `adminPort`
- `defaultHttpEndpoint`

### セキュリティ
- SSL 関連設定の場所
- keystore / truststore 参照
- パスワードの変数参照

### アプリ配置
- `<application>`
- `<webApplication>`
- `<enterpriseApplication>`
- `<springBootApplication>`
- `dropins` の使用有無
- `location` の存在確認

### 変数
- `${...}` の参照
- 定義元候補
- 名前揺れや未定義の可能性

## 4. Maven / Gradle の見方

### Maven らしさ
- `pom.xml`
- `mvnw`
- `.mvn/`
- `liberty-maven-plugin`
- `liberty:dev` を示す README や設定

### Gradle らしさ
- `build.gradle`
- `build.gradle.kts`
- `gradlew`
- Liberty プラグイン適用
- `libertyDev` 相当の痕跡

両方ある場合:
- wrapper の有無
- `target/` または `build/` の痕跡
- README や CI 設定
を見て主系統を推定する。

## 5. feature の簡易所見として扱うもの

以下は「断定」ではなく「怪しい兆候」として扱う。

- feature が多すぎる
- `webProfile` などプロファイル feature がある
- `generated-features.xml` がある
- `mp*` が多いのに関連設定が見当たりにくい
- Jakarta / Java EE 世代の混在が疑われる