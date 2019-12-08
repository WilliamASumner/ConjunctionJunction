#!/bin/bash
if [ ! $# -eq 1 ]; then
    echo "Error: please run with a package name"
    exit 1
else
    files=`ls Tkc*.java`
    echo "package $1;" > /tmp/pack.txt
    package="/tmp/pack.txt"
    temp="/tmp/temp"

    for file in ${files[@]}; do
        cat $package $file > $temp
        mv $temp ./$file
    done
fi
