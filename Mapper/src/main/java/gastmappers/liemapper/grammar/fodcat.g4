/***********************************************
LIE++ grammar
************************************************/

grammar fodcat;
/***********************************************
Parser Rules
************************************************/

/***********************************************
Initial Rule
************************************************/
/**superProgram: program ; **/

program: (assignment | gpio_declaration | array_declaration | function_declaration)* (main_function?);

//Para la función principal existe una forma especifica de definir parametros
main_function: FUNCTION_DEC MAIN_NAME '(' ')' command* END;

/***********************************************
Commands
************************************************/
command:
      ifstat (elsestat+)? END
    | WHILE '(' (logic_exp | gpio) ')' DO command* END
    | REPEAT '(' (int_literal | identifier | arithmetic_exp) ')' TIMES command* END
    | PRINT '(' expression (print_op expression)*')'
    | RETURN expression?
    | func_call
    | flow_exp
    | assignment
    | array_declaration
    | gpio
    | sleep;

ifstat: IF '(' (logic_exp | gpio) ')' THEN command*; /*gpio por si es boton.presionado */
elsestat: ELSE (ifstat | command*);

func_call: identifier '(' parameters_called ')';

flow_exp: BREAK | CONTINUE;

assignment: (identifier | array_call) ASSIGN_OP expression;

gpio: identifier '.' (func_button | func_led | func_servo) (('(' ')') | ASSIGN_OP expression)?;

sleep: SLEEP '(' expression ')';

/***********************************************
Expressions
Son todas las estructuras que al evaluarlas
retornan un valor.
************************************************/
expression: identifier 
    | array_call
    | constant
    | strg
    | func_call
    | arithmetic_exp 
    | logic_exp 
    | relational_exp;

arithmetic_exp: sign? ((int_literal | float_literal | constant | identifier | array_call )
    | '(' arithmetic_exp ')')
    | arithmetic_exp arithmetic_op arithmetic_exp;

relational_exp: (arithmetic_exp | bool_literal) relational_op (arithmetic_exp | bool_literal)
    | relational_exp relational_op relational_exp
    | '(' relational_exp ')';

logic_exp: NOT? (identifier | array_call | bool_literal | relational_exp
    | '(' logic_exp ')')
    | logic_exp logic_op logic_exp;

array_call: identifier'[' arithmetic_exp ']';

/***********************************************
Declarations
************************************************/
function_declaration: FUNCTION_DEC identifier 
      '(' parameters_declaration ')' command* END;

array_declaration: identifier ASSIGN_OP '[' arithmetic_exp ']';

gpio_declaration: identifier ASSIGN_OP (BUTTON | LED ('.' PWMLED)? | SERVO) '(' parameters_called ')'; /*PWMLED es para regular intensidad, y lo ultimo es para el servo angular */

/***********************************************
parameters
************************************************/
parameters_declaration: (identifier ('['']')? (COMMA identifier ('['']')?)*)?;
parameters_called: (expression (COMMA expression)*)?;

/***********************************************
literals
************************************************/
int_literal: INTEGER;
bool_literal: 'verdadero' | 'falso';
float_literal: INTEGER '.' INTEGER;
strg: STRING;
constant: PI | EULER;

/***********************************************
Operators
************************************************/
relational_op: GREATER | GREATEREQ| LESS | LESSEQ | EQUAL | DIFFERENT ;
logic_op: AND | OR | NOT;
arithmetic_op: PLUS | MINUS | MULTIPLICATION | DIVISION | POWER | MODULE;
print_op: PLUS;
sign: PLUS | MINUS; // Agregar signos a opreaciones que requieran numerales

/***********************************************
                    TOKENS
************************************************/
/***********************************************
Math operator tokens
************************************************/
PLUS: '+';
MINUS: '-';
MULTIPLICATION: '*';
DIVISION: '/';
POWER: '**';
MODULE: 'mod';
ASSIGN_OP:':=';

/***********************************************
Math constant tokens
************************************************/
PI: 'pi';
EULER: 'euler';

/***********************************************
Math relational tokens
************************************************/
GREATER: '>';
GREATEREQ: '>=';
LESS: '<';
LESSEQ: '<=';
EQUAL: '=' | 'igual';
DIFFERENT: '!=' | 'diferente';

/***********************************************
Logic operator tokens
************************************************/
OR: '|' | 'o';
AND: '&' | 'y';
NOT: '!' | 'no';

/***********************************************
Simbols tokens
************************************************/
COMMA: ',';
COLON: ':';

/***********************************************
Keywords tokens
************************************************/

//cycles----------------------------------------
WHILE: 'mientras';
REPEAT: 'repetir';
DO: 'haga';
TIMES: 'veces';
BREAK: 'finciclo'; // terminar
CONTINUE: 'continuar';
//conditionals----------------------------------
IF:'si';
THEN: 'entonces';
ELSE: 'sino';
//functions-------------------------------------
FUNCTION_DEC: 'para'; // funcion
MAIN_NAME: 'principal';
END: 'fin';
RETURN : 'devolver';
PRINT: 'imprimir';

//gpio type-------------------------------------
BUTTON: 'boton';
LED: 'led';
PWMLED: 'regulable';
SERVO: 'servo';
//gpio functions--------------------------------
func_button: PRESS;
func_led: ON | OFF | BLINK | VALUE;
func_servo: MIN | MID | MAX | ANGLE;

SLEEP: 'esperar';
//Button
PRESS: 'presionado';
//Led
ON: 'encendido';
OFF: 'apagado';
BLINK: 'parpadear';
VALUE: 'brillo';
//Servo
MIN: 'minimo';
MID: 'medio';
MAX: 'maximo';
ANGLE: 'angulo';

/***********************************************
Data types and identifiers
************************************************/
identifier: IDENTIFIER;
IDENTIFIER: ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;
INTEGER : [0-9]+ ;
STRING: '"' ~('"')+ '"';
//type_specifier:   ('entero' | 'decimal' | 'texto' | 'logico');

/***********************************************
COMENTS AND WHITE SPACE
************************************************/
WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT: '//' ~[\r\n]* -> channel(HIDDEN);