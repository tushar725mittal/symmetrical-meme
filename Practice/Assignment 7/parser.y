%{
#include <stdio.h>
extern int yylex();
void yyerror(const char *s);
%}

%token IF ELSE OPEN_PAREN CLOSE_PAREN IDENTIFIER NUMBER ASSIGN_OP SEMICOLON

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
    | IDENTIFIER ASSIGN_OP IDENTIFIER;
    ;

condition:
    IDENTIFIER
    | NUMBER
    ;

%%

void yyerror(const char *s){
    fprintf(stderr, "Error: %s\n", s);
}

int main(){
    if(!yyparse()){
        printf("Valid if-else statements!\n");
    }
    else{
        printf("Invalid if-else statements!\n");
    }

    return 0;
}

