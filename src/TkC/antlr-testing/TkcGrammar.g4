/* ANTLR grammar/lexer definition
 * Written by Will Sumner
*/

grammar TkcGrammar;

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
    If '(' condition ')' Then statementlist Endif ';'
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

assignedattribute // can change more
:
      SwitchState
    | Authority
    | CrossingState
    | Occupancy
;

// Possible values to check for and assign

value
:
      SwitchStateValue
    | BlockID
    | CrossingStateValue
    | SignalStateValue
    | OccupancyValue
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

BlockID
: [A-Z] DIGIT+
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

fragment LParen
: '('
;
fragment RParen
: ')'
;
fragment DIGIT
: [0-9]
;

