package com.thirds.adl.interpreter.parse.exception;

import com.thirds.adl.interpreter.parse.Parser;
import com.thirds.adl.interpreter.phrase.PhraseType;

public class UnhandledPhraseException extends ParserException {

    private PhraseType phraseType;

    public UnhandledPhraseException(Parser parser, PhraseType phraseType) {

        super(parser);
        this.phraseType = phraseType;
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        System.err.println("The phrase type " + phraseType.name() + " was not handled by the Parser.");
    }

    @Override
    public String getExceptionName() {
        return "UnhandledPhraseException";
    }
}
