package com.mygdx.game.desktop;

import WithoutTestRunner.GameSettings.GameSettings;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ExampleGame;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
        GameSettings coustomSettings = new GameSettings();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Drop";
        config.width = ExampleGame.customSettings.GetWindowWidth();
        config.height = ExampleGame.customSettings.GetWindowHeight();
		new LwjglApplication(new MyGdxGame(), config);
	}
}
