# where-is-me

where is hkimura, now?

## Unreleased
- メソッドの実装

## 0.1.0 - 2021-09-27
* インタフェースだけ。/lists と /list/yyyy-mm の辺がわかりにくいか。
  * post / -- 現在地を記録
  * get  / -- 今日はどこにいるかを表示
  * get  /lists -- 全てのデータを表示
  * get  /list/yyyy-mm -- yyyy-mm にマッチするデータを表示

## was

### 0.1.6 - 2020-12-30
* 日付が出ていなかった。何も修正してないが、
  lein ancient upgrade :check-clojure して
  [fipp "0.6.23"]
  [hawk "0.2.11"]
  を追加。

### 0.1.5 - 2020-08-04
* --body for w.sh
* make client

### 0.1.4 - 2020-07-19
* improve 半角空白を含むメッセージ

### 0.1.3 - 2020-07-18
* if $1 exists, wa.sh posts else gets.
* wa.sh の出力を静かに

### 0.1.2 - 2020-07-15
* fix bump-version.sh

### 0.1.1 - 2020-07-15
* client wa.sh
* fix SimpleDateFormat
* add
    * bump-version.sh
    * start.sh
    * stop.sh
    * whereabouts.service

### 0.1.0 - 2020-07-15
server
* http :3000/
* http post :3000/ loc=whereabouts

## Legal

Copyright © 2021 hiroshi.kimura.0331@gmail.com
