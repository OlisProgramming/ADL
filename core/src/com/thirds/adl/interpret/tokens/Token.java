package com.thirds.adl.interpret.tokens;

public class Token {

    private TokenType tokenType;
    private String value;

    Token(TokenType tokenType, String value) {

        this.tokenType = tokenType;
        this.value = value;
    }

    @Override
    public String toString() {

        return String.format("Token(%s, %s)", tokenType.toString(), (value.equals("")) ? "[[nothing]]" : value);
    }
}
