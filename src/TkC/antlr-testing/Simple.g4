
grammar Simple;

line
: BlockID '=' BlockID ';'
;

BlockID
: [A-Z] DIGIT+
;

Whitespace
: [ \r\n\t] + -> skip
;

fragment DIGIT
: [0-9]
;

