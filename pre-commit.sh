#!/bin/bash

git stash -q --keep-index

echo "Runnig pre-commit hook"

./gradlew ktlint

RESULT=$?

git stash pop -q

[$RESULT -ne 0] && exit 1
exit 0