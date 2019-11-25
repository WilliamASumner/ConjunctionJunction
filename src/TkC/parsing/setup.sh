#!/bin/bash
if [[ "$SHLVL" != "1" ]]; then
   echo "Run this script with source please."
   exit
fi

BASEPATH=`pwd | grep -o ".*ConjunctionJunction"`
LIBPATH="$BASEPATH/lib"
OS=`uname`

if [ -z $ANTLRPATH ]; then
    if [[ "$OS" == "Darwin" ]]; then
    export CLASSPATH=".:$LIBPATH/antlr-4.7.2-complete.jar:$BASEPATH/bin:$CLASSPATH"
    else
    export CLASSPATH=".;$LIBPATH/antlr-4.7.2-complete.jar;$BASEPATH/bin;$CLASSPATH"
    fi

fi

export ANTLRPATH=`realpath --relative-to $(pwd) $LIBPATH/antlr-4.7.2-complete.jar`

if [[ "$OS" == "Darwin" ]]; then
    alias grun='java -Xmx500M -cp ".:$ANTLRPATH" org.antlr.v4.gui.TestRig'
    alias antlr4='java -Xmx500M -cp ".:$ANTLRPATH" org.antlr.v4.Tool'
else
    alias antlr4='java -Xmx500M -cp ".;$ANTLRPATH" org.antlr.v4.Tool'
    alias grun='java -Xmx500M -cp ".;$ANTLRPATH" org.antlr.v4.gui.TestRig'
fi
alias cleanup='rm Tkc*.{java,interp,tokens,class}'
alias compile='javac -cp $ANTLRPATH Tkc*.java '
