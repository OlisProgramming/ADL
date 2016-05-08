package com.thirds.adl.interpreter.phrase;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.thirds.adl.interpreter.phrase.exception.InvalidPhraseException;
import com.thirds.adl.interpreter.phrase.exception.PhraseException;
import com.thirds.adl.interpreter.tokens.Token;
import com.thirds.adl.interpreter.tokens.TokenType;
import org.jetbrains.annotations.NotNull;

/**
 * Generates a batch of new phrases from a token input queue.
 * @see Token
 * @see com.thirds.adl.interpreter.tokens.Tokenizer
 */
public class PhraseGenerator {

    private Queue<Token> tokenQueue;
    public Array<Token> args;
    public String fileName;
    public String fileContents;
    public Queue<Phrase> phrases;

    public int line;
    public int column;

    private boolean eof = false;

    public PhraseGenerator(Queue<Token> tokenArray, String fileName, String fileContents) {

        this.tokenQueue = tokenArray;
        this.fileName = fileName;
        this.fileContents = fileContents;
        try {
            while (!eof) {
                phrases.addLast(popNextPhrase());
            }
        } catch (PhraseException e) {
            e.printStackTrace();
        }
    }

    private void getNextArg() {

        Token nextToken = tokenQueue.removeFirst();
        if (nextToken.getTokenType() == TokenType.EOF) {
            eof = true;
        }
        line = nextToken.getLine();
        column = nextToken.getColumn();
        args.add(nextToken);
    }

    /**
     * Returns whether the next argument (Token) has the type of tokenType.
     * @param tokenType the type of token that is being compared with
     * @return whether the types are equivalent
     */
    private boolean isNextArg(TokenType tokenType) {
        return args.peek().getTokenType() == tokenType;
    }

    @NotNull
    private Phrase popNextPhrase() throws InvalidPhraseException {

        args = new Array<>();
        getNextArg(); /* Arg 1 */
        if (isNextArg(TokenType.VAR_TYPE_TEXT)) {
            getNextArg(); /* Arg 2 */
            if (isNextArg(TokenType.STR_NAME)) {
                getNextArg(); /* Arg 3 */
                if (isNextArg(TokenType.OPR_EQUALS)) {
                    getNextArg(); /* Arg 4 */
                    if (isNextArg(TokenType.STR_LITERAL)) {
                        getNextArg(); /* Arg 5 */
                        if (isNextArg(TokenType.SEMICOLON)) {
                            return new Phrase(PhraseType.VAR_TO_VAL_ASSIGNMENT, args);
                        } else {
                            throw new InvalidPhraseException(this);
                        }
                    } else {
                        throw new InvalidPhraseException(this);
                    }
                } else {
                    throw new InvalidPhraseException(this);
                }
            } else {
                throw new InvalidPhraseException(this);
            }
        } else if (isNextArg(TokenType.FNC_PRINT)) {
            getNextArg(); /* Arg 2 */
            if (isNextArg(TokenType.STR_NAME)) {
                getNextArg(); /* Arg 3 */
                if (isNextArg(TokenType.SEMICOLON)) {
                    return new Phrase(PhraseType.PRINT_VAR, args);
                } else {
                    throw new InvalidPhraseException(this);
                }
            } else {
                throw new InvalidPhraseException(this);
            }
        } else if (isNextArg(TokenType.EOF)) {
            return new Phrase(PhraseType.EOF, args);
        } else {
                throw new InvalidPhraseException(this);
        }
    }
}
