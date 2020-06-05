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
    DOUBLE_DOT,
    DOT_COMA,

    // ID
    ID,
    ID_FOUND_ESCAPE,

    // Variable
    VAR_GLOBAL,
    VAR_INSTANCE,
    VAR_CLASS,
    VAR_LOCAL,

    // LIMITER
    END_STATEMENT

    // UNKNOWN
}