package com.thirds.adl.interpreter.tokens;

public class Token {

    private TokenType tokenType;
    private Object value;

    /** The line at which this token occurs in the file. */
    private int line;
    /** The column at which this token occurs in the file. */
    private int column;

    public Token(TokenType tokenType, Object value, int line, int column) {

        setTokenType(tokenType);
        setValue(value);
        setLine(line);
        setColumn(column);
    }

    @Override
    public String toString() {

        return String.format("Token(%s, %s)", tokenType.toString(), (value.equals("")) ? "[[nothing]]" : value);
    }

    public TokenType getTokenType() {
        return tokenType;
    }
    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
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
}
