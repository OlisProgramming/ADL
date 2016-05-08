package com.thirds.adl.interpreter;

import com.badlogic.gdx.Gdx;
import com.thirds.adl.AppDevLanguage;
import com.thirds.adl.interpreter.phrase.PhraseGenerator;
import com.thirds.adl.interpreter.tokens.Tokenizer;

public class Interpreter {

    public Interpreter(AppDevLanguage game) {

        Gdx.app.log("Interpreter", "Initialise");
        Tokenizer tokenizer = new Tokenizer("Main", game);
        PhraseGenerator generator = new PhraseGenerator(
                tokenizer.tokenQueue,
                tokenizer.fileName,
                tokenizer.fileContents);
    }
}
