# where-is-me

where is hkimura, now?

## Unreleased
* 404? 401?
  (catch Exception e
    [::response/unauthorized (.getMessage e)])))
  willreturn 404. Exception occurs anywhere else?

## 0.2.5
### Changed
* 環境変数 W_I_M で secret の照合をする。
### Added
* simpe authorizing endpoint from where can send current location.
  had better `w.hkim.jp` from smart-phones.
* created ~/.config/httpie/sessions/w.hkim.jp on m1 and m2 by
 `w.hkim.jp at-home`
 on m3, the file is ~/.httpie/sessions/w.hkim.jp
* use $0 as URI? not impossible.

## 0.2.3-SNAPSHOT

## 0.2.2 - 2021-09-27
### Changed
endpoints changed.
* get /
* get /loc
* get /locs
* get /locs/yyyy-mm
* get /html
* get /version

`post /` was not changed.

## 0.2.1 - 2021-09-27
* get /version
* get / returns HTML
* get /find returns JSON

## 0.2.0 - 2021-09-27
### Added boundary.locations.clj
### Fixme
* jdbc で `timestamp like ?%` を実行したいが、できねー。
  現在は like ? なしで受け取ったベクタをフィルタしている。

## 0.1.0 - 2021-09-27
* インタフェースだけ。/lists と /list/yyyy-mm の辺がわかりにくいか。
  * post / -- 現在地を記録
  * get  / -- 今日はどこにいるかを表示
  * get  /list -- 全てのデータを表示
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

Copy free.

Copyright © 2021 hiroshi.kimura.0331@gmail.com
