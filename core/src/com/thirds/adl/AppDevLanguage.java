package com.thirds.adl;

import com.badlogic.gdx.Game;
import com.thirds.adl.screen.MainScreen;

public class AppDevLanguage extends Game {

	@Override
	public void create() {

        setScreen(new MainScreen(this));
    }
}
