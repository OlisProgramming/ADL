package com.thirds.adl;

import com.badlogic.gdx.Game;
import com.thirds.adl.screen.MainScreen;
import com.thirds.adl.screen.TestScreen;

public class AppDevLanguage extends Game {

    private boolean testing = false;

    public AppDevLanguage(String arg) {

        if (arg.equals("Test")) testing = true;
    }

	@Override
	public void create() {

        if (testing) setScreen(new TestScreen());
        else setScreen(new MainScreen(this));
    }
}
