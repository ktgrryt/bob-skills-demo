# Liberty Doctor Output Template

診断結果は、必ず次の順番で出力する。
読みやすさを最優先し、冗長になりすぎないこと。

---

## 1. 診断対象の確定情報

以下を箇条書きで簡潔に出す。

- 探索ルート
- 対象 `server.xml`
- 見つかった主要設定
  - `configDropins/defaults`
  - `configDropins/overrides`
  - `bootstrap.properties`
  - `jvm.options`
  - `server.env`
  - `liberty.env`
- 見つかったログ
  - `messages.log`
  - `console.log`
  - `ffdc/` の有無

---

## 2. サマリー（結論ファースト）

次の 3 段階で全体感を示す。

- 🟢 問題は大きくなさそう
- 🟡 要確認ポイントあり
- 🔴 起動阻害の疑いが強い

続けて、上位 3 件の所見を出す。  
各所見には、根拠となるファイルまたはログを必ず添える。

### 例
- 🔴 `bootstrap.properties` に見当たらない変数 `${http.port}` を `server.xml` が参照している
- 🟡 `configDropins/overrides` 側に HTTP endpoint の上書き候補がある
- 🟡 最新 `messages.log` にポート使用中らしいエラーがある

---

## 3. 観察結果（カテゴリ別）

以下のカテゴリに分けて、短く要点だけ示す。

### 設定の散らばり
- どこに何が書いてあるか
- overrides が上書きしそうか
- include の参照先はどこか

### ビルド / プラグイン
- Maven か Gradle か
- Liberty プラグインらしき設定
- dev mode 前提とのズレがあるか

### feature（簡易所見）
- feature 一覧の要約
- 多すぎる / generated-features.xml / プロファイル feature の有無

### ポート / デプロイ / 変数
- ポート競合の疑い
- `location` や `contextRoot` の疑い
- 未解決変数の疑い

### ログ起因の失敗候補
- ログの短い引用
- そのログが示すカテゴリ
- 次に確認すべきこと

---

## 4. すぐ試せる次アクション

2〜5 個に絞って提示する。

例:
- `${db.user}` の定義元を `bootstrap.properties` / `server.env` / `jvm.options` で確認する
- 競合している 9080 番ポートを変更するか、利用中プロセスを停止する
- `webApplication location="..."` の相対パス基準を見直す
- `configDropins/overrides` の endpoint 定義を先に確認する

ファイル編集はしない。必要なら修正案だけ出す。

---

## 5. 追加質問（必要最小限）

質問は最大 2 個まで。  
本当に必要なときだけ聞く。

例:
- `server.xml` が複数あります。今回の診断対象はどちらですか？
- 主に使っているのは Maven と Gradle のどちらですか？

---

## 6. feature 誘導文（必要時は必須）

以下のどれかに当てはまる場合、最後に必ず付ける。

- feature が多い
- `generated-features.xml` がある
- feature 系エラーがログにある
- 不要 feature を減らしたい意図が見える