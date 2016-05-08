package com.thirds.adl.interpret.tokens;

enum TokenType {

    /** Any valid name (all characters must be letters, upper or lower case, with no other characters),
     * e.g. <pre>projectName</pre> */
    NAME,

    /** Any string literal between double quotes, e.g. <pre>"Hello, World!"</pre> */
    STRING_LITERAL,

    /** The keyword 'text'. Used as a variable type. */
    VAR_TYPE_TEXT,

    /** The variable assignment operator. NOT used as an equality operator. */
    EQUALS,

    /** End of Statement marker. */
    SEMICOLON,

    /** End of File marker. */
    EOF
}
