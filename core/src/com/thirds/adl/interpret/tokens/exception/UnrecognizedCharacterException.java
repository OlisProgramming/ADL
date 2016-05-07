package com.thirds.adl.interpret.tokens.exception;

import com.thirds.adl.interpret.tokens.Tokenizer;

public class UnrecognizedCharacterException extends TokenizerException {

    private char character;

    public UnrecognizedCharacterException(Tokenizer tokenizer, char character) {

        super(tokenizer);
        this.character = character;
    }

    @Override
    public void printStackTrace() {

        System.err.println("Exception UnrecognizedCharacterException");
        super.printStackTrace();
        System.err.println("Unrecognized Character: " + character + " is not a valid token character.");
    }
}
