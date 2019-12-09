#!/bin/bash
var=`java --version | grep "openjdk 13"`

if [ -z "$var" ]; then
    var=`java --version | grep "java 9.0.1"`
    if [ -z "$var" ]; then 
        echo "ERROR: openjdk 13 required"
        exit 1
    fi
else
    echo "openjdk 13 found"
fi
