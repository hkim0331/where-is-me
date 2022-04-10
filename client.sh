#!/bin/sh
#
# * 2020-11-19 use gsed if found.
# * 2021-09-27 API changed. gsed is no longer required.
# * 2021-09-30 session

URL=https://API.add.ress
SESSION=where-is-me

if [ -z "$1" ]; then
  http --body ${URL}/loc
else
  http --body --session where-is-me ${URL}/ loc="$*"
fi
