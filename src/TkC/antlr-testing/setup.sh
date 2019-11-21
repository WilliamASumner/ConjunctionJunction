#!/bin/bash
if [[ "$SHLVL" != "1" ]]; then
    echo "Run this script with source please."
    exit
fi
export CLASSPATH=".:/home/georg/ConjunctionJunction/lib/antlr-4.7.2-complete.jar:$CLASSPATH"
export ANTLRPATH=`realpath --relative-to $(pwd) /home/georg/ConjunctionJunction/lib/antlr-4.7.2-complete.jar`
alias antlr4='java -Xmx500M -cp ".;$ANTLRPATH" org.antlr.v4.Tool'
alias grun='java -Xmx500M -cp ".;$ANTLRPATH" org.antlr.v4.gui.TestRig'
alias cleanup='rm TkcGrammar*.{java,interp,tokens,class}'
alias compile='javac -cp $ANTLRPATH TkcGrammarBaseListener.java TkcGrammarLexer.java TkcGrammarListener.java TkcGrammarParser.java'
