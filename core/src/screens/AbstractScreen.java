package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import constants.ScreenCon;


public abstract class AbstractScreen extends Stage implements Screen {
	
	private Skin skin;
	
	protected AbstractScreen() {
		super(new StretchViewport(ScreenCon.GAME_WIDTH, ScreenCon.GAME_HEIGHT, new OrthographicCamera()));
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
	}
	
	public Skin getSkin() {	return skin;	}
	
	public abstract void buildStage();
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.act(delta);
		super.draw();
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		getViewport().update(width, height, true);
	}
	
	@Override public void hide() {}
	@Override public void pause() {}
	@Override public void resume() {}
	
}