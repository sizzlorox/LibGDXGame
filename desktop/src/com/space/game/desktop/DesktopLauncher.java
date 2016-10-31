package com.space.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.space.game.SpaceGame;

import constants.ScreenCon;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SpaceGame";
		config.width = ScreenCon.GAME_WIDTH;
		config.height = ScreenCon.GAME_HEIGHT;
		config.vSyncEnabled = false;
		new LwjglApplication(new SpaceGame(), config);
	}
}