"  START    "   "
"  MOVEM   AREG, S1
L1    DIV    BREG, S2
"   MOVEM    BREG, S1
L2    EQU    L1
"   MOVEM    BREG, S1
S1     DC     '4'   "
S2     DS     '3'   "
    END       