package com.thirds.adl.interpreter.tokens.exception;

import com.thirds.adl.interpreter.tokens.Tokenizer;

public class UnfinishedStringLiteralException extends TokenizerException {

    private String string;

    public UnfinishedStringLiteralException(Tokenizer tokenizer, String string) {

        super(tokenizer);
        this.string = string;
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println("String: \"" + string + "\" was not finished before the End Of File.");
    }

    @Override
    public String getExceptionName() {
        return "UnfinishedStringLiteralException";
    }
}
