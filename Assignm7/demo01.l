%{
#include "y.tab.h"
%}
%%
[0-9]+ {return NUMBER;}
[a-zA-Z]* {return ID;}
\n {return NL;}
. {return yytext[0];}
%%