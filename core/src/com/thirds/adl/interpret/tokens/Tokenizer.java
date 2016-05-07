package com.thirds.adl.interpret.tokens;

import com.badlogic.gdx.Gdx;
import com.thirds.adl.file.AdlFiles;
import com.thirds.adl.interpret.tokens.exception.TokenizerException;
import com.thirds.adl.interpret.tokens.exception.UnrecognizedCharacterException;
import org.jetbrains.annotations.NotNull;

public class Tokenizer {

    /**
     * String containing all tokens to be interpreted.
     */
    public String fileContents;

    /**
     * Name of file, equivalent to path in constructor.
     */
    public String fileName;

    /**
     * Position counter on fileContents.
     * 0 is the start of the file.
     */
    private int pos = 0;

    /**
     * Line number in fileContents.
     * 1 is the first line.
     */
    public int line = 1;

    /**
     * Column number in each line.
     * 1 is the first column.
     */
    public int column = 1;

    /**
     * Current character at index pos of fileContents.
     */
    private char currentChar;

    /**
     * Was the last token a \r token?
     * (to check for \r\n)
     */
    private boolean carriageReturnLastToken = false;

    public Tokenizer(String path) {

        fileName = path;
        fileContents = AdlFiles.getAdlFile(path).readString();
        currentChar = fileContents.charAt(0);
        Gdx.app.debug("FileInterpreter parse file " + path + ".adl", "\n" + fileContents);
        try {
            Gdx.app.log("Tokenizer", popNextToken().toString());
            Gdx.app.log("Tokenizer", popNextToken().toString());
            Gdx.app.log("Tokenizer", popNextToken().toString());
            Gdx.app.log("Tokenizer", popNextToken().toString());
        } catch (TokenizerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Advance position counter on fileContents.
     * Also set currentChar variable.
     */
    private void advance() {

        pos++;
        if (pos >= fileContents.length()) {
            currentChar = Character.MIN_VALUE; /* Evaluates to \u0000; indicates EOF (end-of-file) */
        } else {
            currentChar = fileContents.charAt(pos);
            if (currentChar == '\r') {
                line++;
                column = 0;
                carriageReturnLastToken = true;
                return;
            } else if (currentChar == '\n') {
                if (!carriageReturnLastToken) {
                    line++;
                    column = 0;
                }
            } else {
                column++;
            }
        }
        carriageReturnLastToken = false;
    }

    private void skipWhitespace() {

        while (currentChar != Character.MIN_VALUE && (Character.isWhitespace(currentChar))) {
            advance();
        }
    }

    /**
     * Pops the next sequence of characters that form a valid name string.
     * @return the sequence.
     */
    @NotNull
    private String popString() {

        String result = "";
        while (currentChar != Character.MIN_VALUE && (Character.isLetter(currentChar))) {
            result += currentChar;
            advance();
            Gdx.app.log("CChar", Character.toString(currentChar));
        }
        return result;
    }

    /**
     * Pops the next token off the fileContents String.
     * @return a new token with value set to the next token's value.
     */
    @NotNull
    private Token popNextToken() throws UnrecognizedCharacterException {

        while (currentChar != Character.MIN_VALUE) {

            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            } else if (Character.isLetter(currentChar)) {
                return new Token(TokenType.STRING, popString());
            }

            throw new UnrecognizedCharacterException(this, currentChar);
        }
        return new Token(TokenType.EOF, "");
    }
}
