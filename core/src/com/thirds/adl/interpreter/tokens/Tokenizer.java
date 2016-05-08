package com.thirds.adl.interpreter.tokens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;
import com.thirds.adl.AppDevLanguage;
import com.thirds.adl.file.AdlFiles;
import com.thirds.adl.interpreter.tokens.exception.TokenizerException;
import com.thirds.adl.interpreter.tokens.exception.UnfinishedStringLiteralException;
import com.thirds.adl.interpreter.tokens.exception.UnrecognizedCharacterException;
import com.thirds.adl.screen.TokenizerErrorScreen;
import org.jetbrains.annotations.NotNull;

/**
 * Extracts tokens of ADL code from a file.
 */
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
     * The Queue of tokens generated by this Tokenizer.
     */
    public Queue<Token> tokenQueue;

    /**
     * Was the last token a \r token?
     * (to check for \r\n)
     */
    private boolean carriageReturnLastToken = false;

    /**
     * Is the program at the end of the file yet?
     */
    private boolean eof = false;

    /**
     * @param path the relative path to the file to be tokenized
     * @param game the AppDevLanguage instance to allow the changeScreen function
     */
    public Tokenizer(String path, AppDevLanguage game) {

        fileName = path;
        fileContents = AdlFiles.getAdlFile(path).readString();
        currentChar = fileContents.charAt(0);
        tokenQueue = new Queue<>();
        Gdx.app.debug("FileInterpreter parse file " + path + ".adl", "\n" + fileContents);
        try {
            while (!eof) {
                tokenQueue.addLast(popNextToken());
            }
        } catch (TokenizerException e) {
            e.printStackTrace();
            game.setScreen(new TokenizerErrorScreen(game, e));
        }
        for (Token tk: tokenQueue) {
            Gdx.app.debug("Token", tk.toString());
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
        }
        return result;
    }

    /**
     * Pops the next sequence of characters that form a valid string literal, surrounded by double quote marks.
     * @return the sequence.
     */
    @NotNull
    private String popStringLiteral() throws UnfinishedStringLiteralException {

        String result = "";
        while (currentChar != Character.MIN_VALUE
                && currentChar != '"') {
            result += currentChar;
            advance();
        }
        if (currentChar == Character.MIN_VALUE) {
            throw new UnfinishedStringLiteralException(this, result);
        }
        advance();
        return result;
    }

    /**
     * Pops the next token off the fileContents String.
     * @return a new token with value set to the next token's value.
     */
    @NotNull
    private Token popNextToken() throws TokenizerException {

        while (currentChar != Character.MIN_VALUE) {

            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
            } else if (Character.isLetter(currentChar)) {
                String word = popString();
                switch (word) {
                    case "text":
                        return new Token(TokenType.VAR_TYPE_TEXT, word, line, column);
                    case "print":
                        return new Token(TokenType.FNC_PRINT, word, line, column);
                    default:
                        return new Token(TokenType.STR_NAME, word, line, column);
                }

            } else if (currentChar == '=') {
                advance();
                return new Token(TokenType.OPR_EQUALS, "=", line, column);

            } else if (currentChar == '"') {
                advance();
                return new Token(TokenType.STR_LITERAL, popStringLiteral(), line, column);

            } else if (currentChar == ';') {
                advance();
                return new Token(TokenType.SEMICOLON, ";", line, column);

            } else {
                /* Not a valid character type */
                throw new UnrecognizedCharacterException(this, currentChar);
            }
        }
        eof = true;
        return new Token(TokenType.EOF, "", line, column);
    }
}
