package com.thirds.adl.interpreter.tokens.exception;

import com.thirds.adl.interpreter.tokens.Tokenizer;

public class UnfinishedMathExpressionException extends TokenizerException {

    public UnfinishedMathExpressionException(Tokenizer tokenizer) {

        super(tokenizer);
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println("Mathematical expression was not finished before the End Of File.");
    }

    @Override
    public String getExceptionName() {
        return "UnfinishedMathExpressionException";
    }
}
