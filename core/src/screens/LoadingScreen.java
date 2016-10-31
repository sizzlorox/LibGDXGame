package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import enums.ScreenEnum;
import handlers.ScreenManager;

public class LoadingScreen extends AbstractScreen {
	
	AssetManager am;
	
	@Override
	public void buildStage() {
	}
	
	@Override
	public void show() {
		super.show();
		am = ScreenManager.getInstance().getManager();
		am.load("data/gfx/bg.png", Texture.class);
		am.load("data/uiskin.atlas", TextureAtlas.class);
		am.finishLoading();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (am.update()) {
			ScreenManager.getInstance().showScreen(ScreenEnum.MAIN_MENU);
		}
		
		float progress = am.getProgress();
		System.out.println(progress);
	}

	@Override
	public void resize(int width, int height) {
		//this.resize(width, height);
		//buttonX = (width - ScreenCon.BUTTON_WIDTH) / 2;
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
