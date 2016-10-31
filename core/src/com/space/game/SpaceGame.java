package com.space.game;

import com.badlogic.gdx.Game;

import enums.ScreenEnum;
import handlers.ScreenManager;

public class SpaceGame extends Game {
	
	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(ScreenEnum.SPLASH_SCREEN);
	}
	
}
