package handlers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;

import enums.ScreenEnum;
import screens.AbstractScreen;

public class ScreenManager {
	
	private static ScreenManager instance;
	private AssetManager am = new AssetManager();
	
	private Game game;
	
	private ScreenManager() {
		super();
	}
	
	public static ScreenManager getInstance() {
		if (instance == null) {
			instance = new ScreenManager();
		}
		return instance;
	}
	
	public void initialize(Game game) {
		this.game = game;
	}
	
	public void showScreen(ScreenEnum screenEnum, Object... params) {
		Screen currentScreen = game.getScreen();
		
		AbstractScreen newScreen = screenEnum.getScreen(params);
		newScreen.buildStage();
		game.setScreen(newScreen);
		
		if (currentScreen != null) {
			currentScreen.dispose();
		}
	}
	
	public AssetManager getManager() {
		return this.am;
	}

}
