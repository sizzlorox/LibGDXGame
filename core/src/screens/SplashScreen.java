package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import enums.ScreenEnum;
import handlers.ScreenManager;

public class SplashScreen extends AbstractScreen {
	
	private float currentY = 280f;

	@Override
	public void buildStage() {
		Label welcomeLabel = new Label("Sizzlor Productions", getSkin());
		welcomeLabel.setX((getWidth() - welcomeLabel.getWidth()) / 2);
		welcomeLabel.setY(currentY + 100);
		addActor(welcomeLabel);
		addAction(Actions.sequence(Actions.fadeIn(2.5f), Actions.fadeOut(2.5f)));
		Timer.schedule(new Task(){
		    @Override
		    public void run() {
		    	ScreenManager.getInstance().showScreen(ScreenEnum.LOADING_SCREEN);
		    }
		}, 5);
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
		getViewport().update(width, height, true);
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		//dispose screen
	}

}