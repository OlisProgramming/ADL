package com.thirds.adl.interpret;

import com.badlogic.gdx.Gdx;
import com.thirds.adl.AppDevLanguage;
import com.thirds.adl.interpret.tokens.Tokenizer;

public class Interpreter {

    public Interpreter(AppDevLanguage game) {

        Gdx.app.log("Interpreter", "Initialise");
        new Tokenizer("Main", game);
    }
}
