package com.thirds.adl.interpreter.tokens.exception;

import com.thirds.adl.interpreter.tokens.Tokenizer;

public class UnstartedMathExpressionException extends TokenizerException {

    public UnstartedMathExpressionException(Tokenizer tokenizer) {

        super(tokenizer);
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println("Mathematical expression was not started (you tried to close it with '>')");
    }

    @Override
    public String getExceptionName() {
        return "NestedMathExpressionException";
    }
}
