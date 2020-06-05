package com.rubylexer;

public enum State {
    START,

    // Punctuation
    COMA,
    DOT,
    LBRACE,
    RBRACE,
    L_FIG_BRACE,
    R_FIG_BRACE,
    L_SQ_BRACE,
    R_SQ_BRACE,
    DOUBLE_DOT,
    DOT_COMA,
    NEW_LINE,

    // ID
    ID,
    ID_START,
    ID_FOUND_ESCAPE,

    // NUMBER
    N_SIGNED,
    NUMBER_START,
    NUMBER_DOUBLE,
    NUMBER_EXP_START,
    NUMBER_EXP,
    NUMBER_ESCAPE,

    // SYMBOL
    SYMBOL,
    SYMBOL_START,

    // OPERATIONS
    OPERATION,

    // LIMITER
    END_STATEMENT

    // UNKNOWN
}