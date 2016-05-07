package com.thirds.adl.interpret;

import com.badlogic.gdx.Gdx;
import com.thirds.adl.interpret.tokens.Tokenizer;

public class Interpreter {

    public Interpreter() {

        Gdx.app.log("Interpreter", "Initialise");
        new Tokenizer("Main");
    }
}
