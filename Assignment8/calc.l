%{
#include "y.tab.h"
extern int yylval;
%}

%%

[0-9]+ { yylval = atoi(yytext); return NUMBER; }
\n { return EOL; }
. { return yytext[0]; }

%%
