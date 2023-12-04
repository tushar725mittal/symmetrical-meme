%{
#include "y.tab.h"
extern int yylval;
%}

%%

"if"    {return IF;}
"else"  {return ELSE;}
"("     {return OPEN_PAREN;}
")"     {return CLOSE_PAREN;}
[a-zA-Z]+ {return IDENTIFIER;}
[0-9]+  {return NUMBER;}
"="     {return ASSIGN_OP;}
";"     {return SEMICOLON;}
.   {/* ignore everything else */}

%%

int yywrap() {
    return 1;
}