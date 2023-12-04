%{
#include<stdio.h>
extern int yylex();
%}

%token NUMBER EOL
%left '-' '+'
%left '*' '/'

%%

calc:
    | calc line
    ;

line: EOL
| exp EOL {printf("%d\n", $1);}
;

exp: NUMBER
| exp '+' exp { $$ = $1 + $3;}
| exp '-' exp { $$ = $1 - $3;}
| exp '*' exp { $$ = $1 * $3;}
| exp '/' exp { $$ = $1 / $3;}
| '(' exp ')' {$$ = $2;}
;

%%

int main(void){
    yyparse();
}

int yyerror(char *s){
    fprintf(stderr, "error: %s\n", s);
}