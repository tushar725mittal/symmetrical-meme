MACRO
INCR &ARG1 &ARG2
ADD AREG &ARG1
ADD BREG &ARG2
MEND
START
MOVER AREG S1
MOVER BREG S1
INCR D1 D2
S1 DC 5
D1 DC 2
D2 DC 3
END