# Liberty Doctor Log Patterns

このファイルは、messages.log や console.log の所見を分類するための補助資料である。
必ずしも断定には使わず、「どのカテゴリの問題か」を整理するために使うこと。

## 1. feature 解決 / 依存

### 典型シグナル
- `CWWKF*`
- feature の依存解決失敗
- 互換性のない feature 組み合わせ
- 必要 feature が足りないように見えるメッセージ

### 伝え方
- feature セットが広すぎる、または不整合の可能性がある
- 厳密整理は `/liberty-feature-min` が適している

## 2. ポート競合

### 典型シグナル
- address already in use
- bind 失敗
- socket open 失敗
- endpoint 起動失敗

### 伝え方
- 競合していそうなポート番号を示す
- そのポートを別プロセスが使っている可能性を示す
- ポート変更 / プロセス停止 / Docker / 別 Liberty の確認を提案する

## 3. アプリ配置ミス

### 典型シグナル
- アーカイブが見つからない
- `location` が無効
- 相対パスが不正
- 同じ `contextRoot` が衝突している可能性

### 伝え方
- `location` を確認する
- 相対パスの基準位置を確認する
- build 成果物の生成場所とのズレを疑う

## 4. SSL / keystore / truststore

### 典型シグナル
- keystore が見つからない
- truststore 読み込み失敗
- パスワード不正
- TLS 初期化失敗

### 伝え方
- ファイルパス
- 変数解決
- パスワード定義元
を優先して確認するよう案内する

## 5. データソース / 外部接続

### 典型シグナル
- JDBC 接続失敗
- ドライバ不足
- LDAP / JMS 接続失敗
- 起動中タイムアウト

### 伝え方
- Liberty 自体の起動失敗なのか
- 外部システム接続失敗なのか
を分けて説明する

## 6. Java バージョン / 互換性

### 典型シグナル
- unsupported class version
- bytecode version mismatch
- `javax` / `jakarta` 系の不一致を示唆する症状
- API / runtime の世代差らしいエラー

### 伝え方
- アプリのコンパイル対象
- 実行 Java
- Liberty feature セット
の整合性確認を案内する

## 7. 権限問題

### 典型シグナル
- access denied
- permission denied
- ログ書き込み不可
- ファイル読み込み不可
- ポートオープン不可

### 伝え方
- ファイル権限
- 実行ユーザー
- マウント先や container 権限
の確認を案内する

## 8. ログ引用時のルール

- 長いログをそのまま貼らない
- 重要な 1〜3 行だけ短く引用する
- 引用したログが「どのカテゴリを示すか」を一言で添える
- 不明な場合は「断定できないが、〜系に見える」と表現する