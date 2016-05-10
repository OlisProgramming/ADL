package com.thirds.adl.interpreter.phrase.exception;

import com.thirds.adl.interpreter.phrase.PhraseGenerator;

public class NoMoreTokensException extends PhraseException {

    public NoMoreTokensException(PhraseGenerator phraseGenerator) {
        super(phraseGenerator);
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println("More tokens were expected.");
    }

    @Override
    public String getExceptionName() {
        return "NoMoreTokensException";
    }
}
