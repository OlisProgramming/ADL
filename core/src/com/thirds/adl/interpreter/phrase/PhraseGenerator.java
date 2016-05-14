package com.thirds.adl.interpreter.phrase;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.thirds.adl.interpreter.phrase.exception.InvalidPhraseException;
import com.thirds.adl.interpreter.phrase.exception.NoMoreTokensException;
import com.thirds.adl.interpreter.phrase.exception.PhraseException;
import com.thirds.adl.interpreter.tokens.Token;
import com.thirds.adl.interpreter.tokens.TokenType;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;

/**
 * Generates a batch of new phrases from a token input queue.
 * @see Token
 * @see com.thirds.adl.interpreter.tokens.Tokenizer
 */
public class PhraseGenerator {

    private Queue<Token> tokenQueue;
    public Array<Token> args;
    public final String fileName;
    public final String fileContents;
    public Queue<Phrase> phrases;

    public int line;
    public int column;

    private boolean eof = false;

    public PhraseGenerator(Queue<Token> tokenArray, final String fileName, final String fileContents)
        throws PhraseException {

        this.tokenQueue = tokenArray;
        this.fileName = fileName;
        this.fileContents = fileContents;
        phrases = new Queue<>();
        while (!eof) {
            phrases.addLast(popNextPhrase());
        }
    }

    private void getNextArg() throws NoMoreTokensException {

        try {
            Token nextToken = tokenQueue.removeFirst();
            if (nextToken.getTokenType() == TokenType.EOF) {
                eof = true;
            }
            line = nextToken.getLine();
            column = nextToken.getColumn();
            args.add(nextToken);

        } catch (NoSuchElementException e) {
            throw new NoMoreTokensException(this);
        }
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
    private Phrase popNextPhrase() throws InvalidPhraseException, NoMoreTokensException {

        args = new Array<>();
        getNextArg(); /* Arg 1 */
        if (isNextArg(TokenType.KWD_TEXT)) { /* Text */
            getNextArg(); /* Arg 2 */
            if (isNextArg(TokenType.STR_NAME)) { /* Text Name */
                getNextArg(); /* Arg 3 */
                if (isNextArg(TokenType.OPR_EQUALS)) { /* Text Name Equals */
                    getNextArg(); /* Arg 4 */
                    if (isNextArg(TokenType.STR_LITERAL)) { /* Text Name Equals StrLit */
                        return new Phrase(PhraseType.VAR_TO_STR_ASSIGNMENT, args, line, column);
                    } else {
                        throw new InvalidPhraseException(this);
                    }
                } else {
                    throw new InvalidPhraseException(this);
                }
            } else {
                throw new InvalidPhraseException(this);
            }
        } else if (isNextArg(TokenType.KWD_INT)) { /* KwdInt */
            getNextArg(); /* Arg 2 */
            if (isNextArg(TokenType.STR_NAME)) { /* KwdInt Name */
                getNextArg(); /* Arg 3 */
                if (isNextArg(TokenType.OPR_EQUALS)) { /* KwdInt Name Equals */
                    getNextArg(); /* Arg 4 */
                    if (isNextArg(TokenType.VAL_INT)) { /* KwdInt Name Equals ValInt */
                        return new Phrase(PhraseType.VAR_TO_INT_ASSIGNMENT, args, line, column);
                    } else if (isNextArg(TokenType.MATH_EXPRESSION)) { /* KwdInt Name Equals Math */
                        return new Phrase(PhraseType.VAR_TO_MATH_ASSIGNMENT, args, line, column);
                    } else {
                        throw new InvalidPhraseException(this);
                    }
                } else {
                    throw new InvalidPhraseException(this);
                }
            } else {
                throw new InvalidPhraseException(this);
            }
        } else if (isNextArg(TokenType.FNC_PRINT)) { /* Print */
            getNextArg(); /* Arg 2 */
            if (isNextArg(TokenType.STR_NAME)) { /* Print Name */
                return new Phrase(PhraseType.PRINT_VAR, args, line, column);
            } else {
                throw new InvalidPhraseException(this);
            }
        } else if (isNextArg(TokenType.FNC_DELETE)) { /* Delete */
            getNextArg(); /* Arg 2 */
            if (isNextArg(TokenType.STR_NAME)) { /* Delete Name */
                return new Phrase(PhraseType.DELETE_VAR, args, line, column);
            } else {
                throw new InvalidPhraseException(this);
            }
        } else if (isNextArg(TokenType.EOF)) {
            return new Phrase(PhraseType.EOF, args, line, column);
        } else {
                throw new InvalidPhraseException(this);
        }
    }
}
