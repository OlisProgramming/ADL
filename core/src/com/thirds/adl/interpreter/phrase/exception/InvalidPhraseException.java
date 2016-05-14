package com.thirds.adl.interpreter.phrase.exception;

import com.badlogic.gdx.utils.Array;
import com.thirds.adl.interpreter.phrase.PhraseGenerator;
import com.thirds.adl.interpreter.tokens.Token;

public class InvalidPhraseException extends PhraseException {

    private Array<Token> tokens;

    public InvalidPhraseException(PhraseGenerator phraseGenerator) {
        super(phraseGenerator);
        this.tokens = phraseGenerator.args;
    }

    @Override
    public void printStackTrace() {

        super.printStackTrace();
        String tokensToString = "";
        for (Token tk: tokens) {
            tokensToString += "\t" + tk.toString() + "\n";
        }
        System.err.println("Phrase:\n" + tokensToString + "is an invalid Phrase of tokens or start of Phrase of tokens.");
    }

    @Override
    public String getExceptionName() {
        return "InvalidPhraseException";
    }
}
