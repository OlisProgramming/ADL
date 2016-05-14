package com.thirds.adl.interpreter.tokens;

public enum TokenType {

    /** Any valid name (all characters must be letters, upper or lower case, with no other characters),
     * e.g. <pre>projectName</pre> */
    STR_NAME,

    /** Any string literal between double quotes, e.g. <pre>"Hello, World!"</pre> */
    STR_LITERAL,

    /** Any integer value. */
    VAL_INT,

    /** The keyword 'text'. Used as a variable type. */
    KWD_TEXT,

    /** The keyword 'int'. Used as a variable type. */
    KWD_INT,

    /** Any mathematical expression, enclosed within &lt;angle brackets&gt;. */
    MATH_EXPRESSION,

    /** The stdlib function 'print'. Can print any variable type overriding toString. */
    FNC_PRINT,

    /** The stdlib function 'delete'. Deletes variables. */
    FNC_DELETE,

    /** The variable assignment operator. NOT used as an equality operator. */
    OPR_EQUALS,

    /** The addition operator. */
    OPR_PLUS,

    /** End of File marker. */
    EOF
}
