#!/bin/bash
var=`java --version | grep "openjdk 13"`

if [ -z "$var" ]; then
    echo "ERROR: openjdk 13 required"
    exit 1
else
    echo "openjdk 13 found"
fi
