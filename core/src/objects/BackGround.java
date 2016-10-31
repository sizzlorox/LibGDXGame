package objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import constants.ScreenCon;

public class BackGround extends Actor {
	
	private TextureRegion textureRegion;

	public BackGround(Texture background) {
		textureRegion = new TextureRegion(background);
		setX(ScreenCon.GAME_WIDTH/2);
		setY(ScreenCon.GAME_HEIGHT/2);
		setWidth(ScreenCon.GAME_WIDTH);
		setHeight(ScreenCon.GAME_HEIGHT);
	}
	
	@Override
	public void draw(Batch bat, float parentAlpha) {
		bat.draw(textureRegion, 0, 0, getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, 0);
	}
	
}
