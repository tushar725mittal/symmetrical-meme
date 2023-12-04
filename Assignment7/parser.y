%{
#include <stdio.h>
extern int yylex();
void yyerror(const char *s);
%}

%token IF
%token ELSE
%token OPEN_PAREN
%token CLOSE_PAREN
%token IDENTIFIER
%token NUMBER
%token ASSIGN_OP
%token SEMICOLON

%nonassoc THEN
%nonassoc ELSE

%%

program:
    program statement
    |
    ;

statement:
    IF OPEN_PAREN condition CLOSE_PAREN statement %prec THEN
    | IF OPEN_PAREN condition CLOSE_PAREN statement ELSE statement
    | assignment SEMICOLON
    ;

assignment:
    IDENTIFIER ASSIGN_OP NUMBER
    ;

condition:
    IDENTIFIER
    | NUMBER
    ;

%%

void yyerror(const char *s) {
    fprintf(stderr, "Error: %s\n", s);
}

int main() {
    if (!yyparse()) {
        printf("Valid if-else statements!\n");
    } else {
        printf("Invalid if-else statements!\n");
    }
    return 0;
}
