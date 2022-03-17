#!/bin/sh
#
# origin: ${where-is-me}/client.sh
#
# * httpie 2.5.0 changed session store.
#   ~/.config/httpie -> ~/.httpie/sessions
# * 2021-09-27 API changed. gsed is no longer required.
# * 2020-11-19 use gsed if found.

URL=https://w.hkim.jp
if [ -z "$1" ]; then
  http --body ${URL}/loc
else
  http --body --session where-is-me ${URL}/ loc="$*"
fi
