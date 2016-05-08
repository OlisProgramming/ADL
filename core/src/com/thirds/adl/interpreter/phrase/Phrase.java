package com.thirds.adl.interpreter.phrase;

import com.badlogic.gdx.utils.Array;
import com.thirds.adl.interpreter.tokens.Token;

class Phrase {

    private PhraseType phraseType;
    private Array<Token> tokens;

    Phrase(PhraseType phraseType, Array<Token> tokens) {

        this.phraseType = phraseType;
        this.tokens = tokens;
    }

    @Override
    public String toString() {

        String tokensToString = "";
        for (Token tk: tokens) {
            tokensToString += tk.toString() + ", ";
        }
        return String.format("Phrase(%s, %s)", phraseType.toString(), tokensToString.substring(0, tokensToString.length()-2));
    }
}
