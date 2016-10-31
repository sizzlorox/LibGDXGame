package enums;

import screens.AbstractScreen;
import screens.GameScreen;
import screens.HallOfFameScreen;
import screens.LoadingScreen;
import screens.MenuScreen;
import screens.OptionsScreen;
import screens.SplashScreen;

public enum ScreenEnum {

	SPLASH_SCREEN {
		public AbstractScreen getScreen(Object... params) {
			return new SplashScreen();
		}
	},
	LOADING_SCREEN {
		public AbstractScreen getScreen(Object... params) {
			return new LoadingScreen();
		}
	},
	MAIN_MENU {
		public AbstractScreen getScreen(Object... params) {
			return new MenuScreen();
		}
	},
	GAME_SCREEN {
		public AbstractScreen getScreen(Object... params) {
			return new GameScreen();
		}
	},
	OPTIONS_SCREEN {
		public AbstractScreen getScreen(Object... params) {
			return new OptionsScreen();
		}
	},
	HOF_SCREEN {
		public AbstractScreen getScreen(Object... params) {
			return new HallOfFameScreen();
		}
	};
	
	public abstract AbstractScreen getScreen(Object... params);
	
}