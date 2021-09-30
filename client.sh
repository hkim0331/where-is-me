#!/bin/sh
#
# * 2021-09-30 session
# * 2021-09-27 API changed. gsed is no longer required.
# * 2020-11-19 use gsed if found.

URL="https://API.add.ress"
if [ -z "$1" ]; then
  http --body ${URL}/loc
else
  http --session ${SESSION} --body ${URL}/ loc="$*"
fi
