package com.thirds.adl.interpreter;

import com.thirds.adl.AppDevLanguage;
import com.thirds.adl.exception.ADLException;
import com.thirds.adl.interpreter.parse.Parser;
import com.thirds.adl.interpreter.parse.exception.ParserException;
import com.thirds.adl.interpreter.phrase.PhraseGenerator;
import com.thirds.adl.interpreter.phrase.exception.PhraseException;
import com.thirds.adl.interpreter.tokens.Tokenizer;
import com.thirds.adl.interpreter.tokens.exception.TokenizerException;
import com.thirds.adl.screen.ErrorScreen;

public class Interpreter {

    private final AppDevLanguage game;

    public Interpreter(AppDevLanguage game) {

        if (game != null) {
            this.game = game;
            System.out.println("\n\n" +
                    "***********************\n" +
                    " ADL Interpreter Begin \n" +
                    "***********************\n");
            try {
                parseFile("Main", false);
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
        } else {
            this.game = null;
        }
    }

    private Parser parseFile(String path, boolean fileIsInternal) throws
            TokenizerException,
            PhraseException,
            ParserException {

        final Tokenizer tokenizer = new Tokenizer(path, fileIsInternal);
        final PhraseGenerator phraseGenerator = new PhraseGenerator(
                tokenizer.tokenQueue,
                tokenizer.fileName,
                tokenizer.fileContents);
        return new Parser(
                phraseGenerator.phrases,
                phraseGenerator.fileName,
                phraseGenerator.fileContents);

    }

    public static Parser test(String file) throws
            TokenizerException,
            PhraseException,
            ParserException {

        return new Interpreter(null).parseFile(file, true);
    }
}
