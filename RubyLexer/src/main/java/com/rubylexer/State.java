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
    NUMBER_START,
    NUMBER_DOUBLE,
    NUMBER_EXP_START,
    NUMBER_EXP,
    NUMBER_ESCAPE,
    NUMBER_RETURN,

    // SYMBOL
    SYMBOL,
    SYMBOL_START,

    // OPERATIONS
    OPERATION,

    // STR LIT
    STR_LIT_SQ,
    STR_MULTILINE,

    // COMMENT
    COMMENT_SINGLE_LINE,
    COMMENT_SINGLE_LINE_END,

    // HEREDOC
    HEREDOC_START,
    HEREDOC_FIRST,
    HEREDOC_SECOND,
    HEREDOC_FOUND_ID,
}