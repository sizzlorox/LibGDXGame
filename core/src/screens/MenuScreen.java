package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import constants.ScreenCon;
import enums.ScreenEnum;
import handlers.ScreenManager;

public class MenuScreen extends AbstractScreen {
	
	private float currentY = 280f;
	private float buttonX;

	@Override
	public void buildStage() {
		buttonX = (getWidth() - ScreenCon.BUTTON_WIDTH) / 2;
		
		Label welcomeLabel = new Label("Welcome to My Game", getSkin());
		welcomeLabel.setX((getWidth() - welcomeLabel.getWidth()) / 2);
		welcomeLabel.setY(currentY + 100);
		addActor(welcomeLabel);
		
		TextButton startGameButton = new TextButton("Start Game", getSkin());
		startGameButton.setX(buttonX);
		startGameButton.setY(currentY);
		startGameButton.setWidth(ScreenCon.BUTTON_WIDTH);
		startGameButton.setHeight(ScreenCon.BUTTON_HEIGHT);
		startGameButton.addListener(new ClickListener() {
			public void clicked(InputEvent e,float x,float y) {
				ScreenManager.getInstance().showScreen(ScreenEnum.GAME_SCREEN);
			}
		});
		addActor(startGameButton);
		
		TextButton optionsButton = new TextButton("Options", getSkin());
		optionsButton.setX(buttonX);
		optionsButton.setY((currentY -= ScreenCon.BUTTON_HEIGHT + ScreenCon.BUTTON_SPACING));
		optionsButton.setWidth(ScreenCon.BUTTON_WIDTH);
		optionsButton.setHeight(ScreenCon.BUTTON_HEIGHT);
		optionsButton.addListener(new ClickListener() {
			public void clicked(InputEvent e,float x,float y) {
				ScreenManager.getInstance().showScreen(ScreenEnum.OPTIONS_SCREEN);
			}
		});
		addActor(optionsButton);
		
		TextButton hallOfFameButton = new TextButton("Hall of Fame", getSkin());
		hallOfFameButton.setX(buttonX);
		hallOfFameButton.setY((currentY -= ScreenCon.BUTTON_HEIGHT + ScreenCon.BUTTON_SPACING));
		hallOfFameButton.setWidth(ScreenCon.BUTTON_WIDTH);
		hallOfFameButton.setHeight(ScreenCon.BUTTON_HEIGHT);
		hallOfFameButton.addListener(new ClickListener() {
			public void clicked(InputEvent e,float x,float y) {
				ScreenManager.getInstance().showScreen(ScreenEnum.HOF_SCREEN);
			}
		});
		addActor(hallOfFameButton);
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		act(delta);
		draw();
	}

	@Override
	public void resize(int width, int height) {
		//this.resize(width, height);
		buttonX = (width - ScreenCon.BUTTON_WIDTH) / 2;
		getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		//dispose();
	}
	
}