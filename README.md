# 4タイプ判定テスト ソースコード

Google App Engine 上で動作中の [4タイプ判定テスト](https://four-types.appspot.com/) のソースコードです。
もともとは Google Code 上で管理していたのですが、コードリポジトリサービスが終了するとのことで、GitHubへほぼ強制的に引っ越ししました。

|ディレクトリ|説明|
|:--|:--|
|`4typechecker`|初版コード、GWTベース。BigTableへ判定結果を保存していたが、利用制限が厳しくなりたまに制限を超えてサービスが止まってしまうことがあったため、現在では使用していない。|
|`four-types`|現在稼働中のコード。オバマ大統領が選出されたスーパーチューズデー直前にAppEngineふくめたクラウドサービスへの無差別DDoS攻撃にあったため、対処療法で通信量を減らしたバージョンを作成。最初の画面をロードすれば、あとはすべてクライアント側で処理するように変更。|
