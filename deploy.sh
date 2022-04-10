#!/bin/sh
if [ -z "$1" ]; then
	echo usage: $0 target/file.jar
	exit 1
fi

BN=`basename $1`
scp $1 hkim.jp:app/where-is-me/ && \
ssh hkim.jp "(cd app/where-is-me && ln -sf ${BN} where-is-me.jar)" && \
ssh hkim.jp "(cd app/where-is-me && ./restart.sh &)"

