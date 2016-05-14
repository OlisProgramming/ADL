package com.thirds.adl.interpreter.tokens.exception;

import com.thirds.adl.interpreter.tokens.TokenType;
import com.thirds.adl.interpreter.tokens.Tokenizer;

public class InvalidMathTokenException extends TokenizerException {

    private TokenType tokenType;

    public InvalidMathTokenException(Tokenizer tokenizer, TokenType tokenType) {

        super(tokenizer);
        this.tokenType = tokenType;
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println("The token type " + tokenType.name() + " is not valid in a mathematical expression.");
    }

    @Override
    public String getExceptionName() {
        return "InvalidMathTokenException";
    }
}
