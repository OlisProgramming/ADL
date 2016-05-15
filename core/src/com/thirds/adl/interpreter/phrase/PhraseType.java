package com.thirds.adl.interpreter.phrase;

public enum PhraseType {

    /** Set a variable to a string literal. */
    VAR_TO_STR_ASSIGNMENT,

    /** Set a variable to an integer. */
    VAR_TO_INT_ASSIGNMENT,

    /** Set a variable to math expression. */
    VAR_TO_MATH_ASSIGNMENT,

    /** Set a variable to the value of another variable. */
    VAR_TO_VAR_ASSIGNMENT,

    /* Print the value of a variable. */
    PRINT_VAR,

    /* Delete a variable. */
    DELETE_VAR,

    /** End of File marker. */
    EOF
}
