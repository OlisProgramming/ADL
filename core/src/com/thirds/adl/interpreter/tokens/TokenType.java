package com.thirds.adl.interpreter.tokens;

public enum TokenType {

    /** Any valid name (all characters must be letters, upper or lower case, with no other characters),
     * e.g. <pre>projectName</pre> */
    STR_NAME,

    /** Any string literal between double quotes, e.g. <pre>"Hello, World!"</pre> */
    STR_LITERAL,

    /** The keyword 'text'. Used as a variable type. */
    VAR_TYPE_TEXT,

    /** Print keyword. Can print strings only (at the moment!) */
    FNC_PRINT,

    /** The variable assignment operator. NOT used as an equality operator. */
    OPR_EQUALS,

    /** End of Statement marker. */
    SEMICOLON,

    /** End of File marker. */
    EOF
}