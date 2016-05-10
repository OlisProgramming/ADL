package com.thirds.adl.interpreter;

import com.thirds.adl.AppDevLanguage;
import com.thirds.adl.interpreter.parse.Parser;
import com.thirds.adl.interpreter.parse.exception.ParserException;
import com.thirds.adl.interpreter.phrase.PhraseGenerator;
import com.thirds.adl.interpreter.phrase.exception.PhraseException;
import com.thirds.adl.interpreter.tokens.Tokenizer;
import com.thirds.adl.interpreter.tokens.exception.TokenizerException;
import com.thirds.adl.screen.ErrorScreen;

public class Interpreter {

    public Interpreter(AppDevLanguage game) {

        System.out.println("\n\n" +
            "***********************\n" +
            " ADL Interpreter Begin \n" +
            "***********************\n");
        try {
            final Tokenizer tokenizer = new Tokenizer("Main");
            final PhraseGenerator phraseGenerator = new PhraseGenerator(
                    tokenizer.tokenQueue,
                    tokenizer.fileName,
                    tokenizer.fileContents);
            final Parser parser = new Parser(
                    phraseGenerator.phrases,
                    phraseGenerator.fileName,
                    phraseGenerator.fileContents);
        } catch (TokenizerException e) {
            e.printStackTrace();
            game.setScreen(new ErrorScreen(game, e, "tokenizing"));
        } catch (PhraseException e) {
            e.printStackTrace();
            game.setScreen(new ErrorScreen(game, e, "phrasing"));
        } catch (ParserException e) {
            e.printStackTrace();
            game.setScreen(new ErrorScreen(game, e, "parsing"));
        }
        System.out.println("\n" +
                "***********************\n" +
                "  ADL Interpreter End  \n" +
                "***********************\n\n");
    }
}
