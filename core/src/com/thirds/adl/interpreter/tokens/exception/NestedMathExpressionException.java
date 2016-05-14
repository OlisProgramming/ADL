package com.thirds.adl.interpreter.tokens.exception;

import com.thirds.adl.interpreter.tokens.Tokenizer;

public class NestedMathExpressionException extends TokenizerException {

    public NestedMathExpressionException(Tokenizer tokenizer) {

        super(tokenizer);
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println("Mathematical expressions aren't nestable.");
    }

    @Override
    public String getExceptionName() {
        return "NestedMathExpressionException";
    }
}
