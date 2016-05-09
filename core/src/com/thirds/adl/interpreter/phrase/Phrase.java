package com.thirds.adl.interpreter.phrase;

import com.badlogic.gdx.utils.Array;
import com.thirds.adl.interpreter.tokens.Token;

public class Phrase {

    private PhraseType phraseType;
    private Array<Token> tokens;
    private int line;
    private int column;

    Phrase(PhraseType phraseType, Array<Token> tokens, int line, int column) {

        setPhraseType(phraseType);
        this.tokens = tokens;
        setLine(line);
        setColumn(column);
    }

    @Override
    public String toString() {

        String tokensToString = "";
        for (Token tk: tokens) {
            tokensToString += tk.toString() + ", ";
        }
        return String.format("Phrase(%s, %s)", phraseType.toString(), tokensToString.substring(0, tokensToString.length()-2));
    }

    public PhraseType getPhraseType() {
        return phraseType;
    }

    public void setPhraseType(PhraseType phraseType) {
        this.phraseType = phraseType;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Array<Token> getTokens() {
        return tokens;
    }

    public void setTokens(Array<Token> tokens) {
        this.tokens = tokens;
    }
}
