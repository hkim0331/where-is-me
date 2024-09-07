#!/bin/sh
PORT=8008 \
JDBC_DATABASE_URL=jdbc:sqlite:db/dev.sqlite \
java -jar where-is-me.jar
echo where-is-me started

