package com.thirds.adl.interpreter.parse;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.thirds.adl.interpreter.parse.exception.ParserException;
import com.thirds.adl.interpreter.parse.exception.UnhandledPhraseException;
import com.thirds.adl.interpreter.phrase.Phrase;
import com.thirds.adl.interpreter.tokens.Token;
import com.thirds.adl.interpreter.variable.VariableHandler;

/**
 * Parses Phrases and runs them.
 * @see Phrase
 */
public class Parser {

    private Queue<Phrase> phrases;

    public int line = 0;
    public int column = 0;
    public final String fileName;
    public final String fileContents;

    private VariableHandler variableHandler;

    public Parser(Queue<Phrase> phrases, final String fileName, final String fileContents) throws ParserException {

        this.phrases = phrases;
        this.fileName = fileName;
        this.fileContents = fileContents;

        variableHandler = new VariableHandler(this);

        for (Phrase phrase: phrases) {
            runPhrase(phrase);
        }
    }

    private void runPhrase(Phrase phrase) throws ParserException {

        line = phrase.getLine();
        column = phrase.getColumn();

        Array<Token> tokens = phrase.getTokens();
        switch (phrase.getPhraseType()) {
            case VAR_TO_STR_ASSIGNMENT:
            case VAR_TO_INT_ASSIGNMENT:
                variableHandler.addVariable(
                        tokens.get(1).getValue().toString(),
                        tokens.get(3).getValue());
                break;

            case PRINT_VAR:
                System.out.println(
                        variableHandler.getVariableValue(
                                tokens.get(1).getValue().toString()));
                break;

            case DELETE_VAR:
                variableHandler.deleteVariable(tokens.get(1).getValue().toString());
                break;

            case EOF:
                break;

            default:
                throw new UnhandledPhraseException(this, phrase.getPhraseType());
        }
    }
}
