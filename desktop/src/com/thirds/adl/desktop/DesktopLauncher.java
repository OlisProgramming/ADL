package com.thirds.adl.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.thirds.adl.AppDevLanguage;

public class DesktopLauncher {
	public static void main(String[] args) {

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		if (args.length > 0) {
            new LwjglApplication(new AppDevLanguage(args[0]), config);
        } else {
            new LwjglApplication(new AppDevLanguage(""), config);
        }
	}
}
