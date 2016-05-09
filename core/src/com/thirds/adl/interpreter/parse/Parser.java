package com.thirds.adl.interpreter.parse;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
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
    public String fileName;
    public String fileContents;

    private VariableHandler variableHandler;

    public Parser(Queue<Phrase> phrases, String fileName, String fileContents) {

        this.phrases = phrases;
        this.fileName = fileName;
        this.fileContents = fileContents;

        variableHandler = new VariableHandler();

        for (Phrase phrase: phrases) {
            runPhrase(phrase);
        }
    }

    private void runPhrase(Phrase phrase) {

        line = phrase.getLine();
        column = phrase.getColumn();

        Array<Token> tokens = phrase.getTokens();
        switch (phrase.getPhraseType()) {
            case VAR_TO_STR_ASSIGNMENT:
                variableHandler.addGlobalVariable(
                        tokens.get(1).getValue(),
                        tokens.get(3).getValue());
                break;

            case PRINT_VAR:
                System.out.println(
                        variableHandler.getGlobalVariableValue(
                                tokens.get(1).getValue()));
                break;

            case EOF:
                break;

            default:
                break;
        }
    }
}
