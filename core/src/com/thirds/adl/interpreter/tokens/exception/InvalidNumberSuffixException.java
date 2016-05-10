package com.thirds.adl.interpreter.tokens.exception;

import com.thirds.adl.interpreter.tokens.Tokenizer;

public class InvalidNumberSuffixException extends TokenizerException {

    private char suffix;

    public InvalidNumberSuffixException(Tokenizer tokenizer, char suffix) {

        super(tokenizer);
        this.suffix = suffix;
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println(suffix + " is not a valid number suffix.");
    }

    @Override
    public String getExceptionName() {
        return "InvalidNumberSuffixException";
    }
}
