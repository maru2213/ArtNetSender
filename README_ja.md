# ArtNetSender
  <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103216.png" />

## このアプリについて
これは、ArtNetを使用してローカルネットワーク上の照明を制御するAndroid向けのアプリです。

## 操作説明
### 初回起動時
- 表示されるダイアログでテーマを決めてください。
- これ以降も好きな時にテーマを変更することができます。

  <img width="300" src="https://artnetsender-general.web.app/images/device-2021-01-22-103116.png" />

- 左がダーク、右がライトのスクリーンショットです。

  <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103216.png" /> <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103230.png" />

- その後、テーマを変更する方法が表示されます。

  <img width="300" src="https://artnetsender-general.web.app/images/device-2021-01-22-103150.png" />

### IPアドレスの設定
- まず、左下にある"SET."ボタンを押してください。

  <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103216.png" />

- これがSetting Modeです。Normal Modeに戻るには"SET."ボタンをもう一度押してください。
- 次に、"SET."ボタンの隣にある"ADD"ボタンを押してください。

  <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103258.png" />

- これはAdding modeです。Setting Modeに戻るには"ADD"ボタンをもう一度押してください。
- "SET."ボタンを押すことで直接Normal Modeに戻ることもできます。
- ここで、ArtNetの信号を送信するIPアドレスを指定します。
- テンキーを使ってIPアドレスを入力し、"PLEASE"ボタンを押してください。

  <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103309.png" />

- すると、入力したIPアドレスが表示されます。
- 複数のIPアドレスを登録したい場合は同じ手順を繰り返してください。
- "BACK"ボタンと"NEXT"ボタンを使って現在登録されているIPアドレスを確認することができます。
- IPアドレスの登録を消去したい場合、"BACK"ボタンと"NEXT"ボタンを使ってそのアドレスを表示し、"DEL"ボタンを押してください。

  <img width="200" src="https://artnetsender-general.web.app/images/device-2021-01-22-103346.png" />

### ArtNet信号の送信
- コマンドを入力して"PLEASE"を押すだけです。

### コマンドの例

- チャンネル1を255にする
```
1 FULL
1 AT FULL
1 AT 255
```

- チャンネル1を0にする
```
1 ZERO
1 AT ZERO
1 AT 0
```

- チャンネル1を123にする
```
1 AT 123
```

- チャンネル1〜5を255にする
```
1 THRU 5 FULL
1 THRU 5 AT FULL
1 THRU 5 AT 255
```

- チャンネル1〜5を0にする
```
1 THRU 5 ZERO
1 THRU 5 AT ZERO
1 THRU 5 AT 0
```

- チャンネル1〜5を123にする
```
1 THRU 5 AT 123
```

- チャンネル1を0に、チャンネル5を255に設定し、チャンネル2〜4の値をその間を等分するように設定する
- 階段みたいな感じ
```
1 THRU 5 AT 0 THRU 255
```

- チャンネル1, 3, 5, 7, 9を255に設定する
```
1 THRU 10 BY 2 FULL
1 THRU 10 BY 2 AT FULL
1 THRU 10 BY 2 AT 255
```

- チャンネル1, 3, 5, 7, 9を0に設定する
```
1 THRU 10 BY 2 ZERO
1 THRU 10 BY 2 AT ZERO
1 THRU 10 BY 2 AT 0
```

- チャンネル1, 3, 5, 7, 9を123に設定する
```
1 THRU 10 BY 2 AT 123
```

- チャンネル1を0に、チャンネル9を255に設定し、チャンネル3, 5, 7の値をその間を等分するように設定する
- こっちも階段みたいな感じ
- 百聞は一見に如かず
```
1 THRU 10 BY 2 AT 0 THRU 255
```

### コマンドダイアグラム
Made by [tabatkins/railroad-diagrams](https://github.com/tabatkins/railroad-diagrams)

  <img src="https://artnetsender-general.web.app/images/command_diagram.svg" />

## ライセンス
このソフトウェアはGPLv3の下でリリースされています。"LICENSE"を参照してください。
