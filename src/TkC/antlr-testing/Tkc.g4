/* ANTLR grammar/lexer definition
 * Written by Will Sumner
*/

grammar Tkc;

program
:
    ifsequence? EOF
;

ifsequence
:
      ifstatement
    | ifsequence ifstatement
;

ifstatement
:
    If '(' conditionlist ')' Then statementlist Endif ';'
;

conditionlist
:
      condition
    | condition OR  conditionlist
    | condition AND conditionlist
;

condition
:  BlockID '.' attribute EEquals value
;

statementlist
:
      statement
    | statementlist statement
;

statement
:
    BlockID '.' assignedattribute Equals value ';'
;

attribute // can only access occupancy or switch position
:
      SwitchState
    | Occupancy
;

assignedattribute // can change switch,authority,crossing,occupancy
:
      SwitchState
    | Authority
    | CrossingState
    | Occupancy
    | Speed
;

// Possible values to check for and assign

value
:
      SwitchStateValue
    | BlockID
    | CrossingStateValue
    | SignalStateValue
    | OccupancyValue
    | SpeedValue
;

SwitchStateValue
:
      FORK
    | MAIN
;

CrossingStateValue
:
      UP
    | DOWN
;

SignalStateValue
:
      RED
    | YELLOW
    | GREEN
;

OccupancyValue
:
      OCCUPIED
    | UNOCCUPIED
;

SpeedValue
:
    FLOAT
;

BlockID
: [A-Z] DIGIT+
;

FLOAT
: [0-9]*'.'[0-9]+
;
Whitespace
: [ \r\n\t]+ -> skip
;

If
: 'if'
| 'IF'
;

Then
: 'then'
| 'THEN'
;

Endif
: 'endif'
| 'ENDIF'
;

Semicolon
: ';'
;

Period
: '.'
;

Speed
: 'speed'
| 'SPEED'
;

Occupancy
: 'occupancy'
| 'OCCUPANCY'
;

SwitchState
:   'switchstate'
  | 'SWITCHSTATE'
;

CrossingState
:   'crossingstate'
  | 'CROSSINGSTATE'
;

Authority
:   'authority'
  | 'AUTHORITY'
;


EEquals
: '=='
;

Equals
: '='
;

FORK
:     'FORK'
    | 'fork'
;

MAIN
:  'MAIN'
  |'main'
;

UP
:   'UP'
  | 'up'
;
DOWN
:    'DOWN'
    |'down'
;
 

RED
:   'RED'
    |'red'
;

YELLOW
:    'YELLOW'
    |'yellow'
;

GREEN
:    'GREEN'
    |'green'
;

OCCUPIED
:    'OCCUPIED'
    |'occupied'
;

UNOCCUPIED
:    'UNOCCUPIED'
    |'unoccupied'
;

OR
:   'or'
   |'OR'
;

AND
:   'and'
   |'AND'
;

LineComment
: '//' ~[\r\n]* -> channel(HIDDEN)
;

fragment LParen
: '('
;
fragment RParen
: ')'
;
fragment DIGIT
: [0-9]
;

