# where-is-me

私は今どこに？
コロナ禍下で受講生が私の居場所を知りやすいように。

whoami コマンドがあるんだから、
プロジェクト名は where-am-i がよかったか？

## mkdir db

リポジトリをクローンしたら、mkdir db しないと sqlite3 のデータベースを作れない。

```shell
  $ git clone https://github.com/hkim0331/where-is-me.git
  $ cd where-is-me
  $ mkdir db
  $ lein repl
  user=> (dev)
  dev=> (go)
```

別のターミナルで、

```shell
  $ http :3000/loc
  $ http :3000/ loc='あやしいところ'
  $ http :3000/loc
  もしくはブラウザで localhost:3000/ をオープン。

  $ http :3000/locs
  $ http :3000/locs/2021-09
```
本番は、

```shell
  $ lein uberjar
  $ PORT=8080 \
    DATABASE_URL="jdbc:sqlite:db/dev.sqlite" \
    java -jar target/where-is-me-standalone.jar
```
のように。

ログが標準出力に出てくるので適当な場所にリダイレクトすること。

## was

whereabouts という名前で melt ローカルリポジトリに作っていた。

ssh://git@git.melt.kyutech.ac.jp/git/whereabouts.git

## Legal

Copy Free.

Copyright © 2021 hiroshi.kimura.0331@gmail.com


