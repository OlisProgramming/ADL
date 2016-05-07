package com.thirds.adl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.*;
import com.thirds.adl.screen.SplashScreen;

public class AppDevLanguage extends Game {

	@Override
	public void create() {

        setScreen(new SplashScreen());
    }
}
