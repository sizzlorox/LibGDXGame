package screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import constants.BulletList;
import constants.EnemyList;
import constants.ScreenCon;
import entities.Enemy;
import entities.Player;
import handlers.ScreenManager;
import objects.BackGround;
import objects.Bullet;

public class GameScreen extends AbstractScreen {
	
	private long FIRE_RATE = 500, LAST_SHOT;
	Random r = new Random();
	AssetManager am;
	Label debug;

	@Override
	public void buildStage() {
		am = ScreenManager.getInstance().getManager();
		BackGround bg = new BackGround(am.get("data/gfx/bg.png", Texture.class));
		addActor(bg);
		Player player = new Player();
		addActor(player);
		debug = new Label("FPS : " + Gdx.graphics.getFramesPerSecond() +" \nEnemy : " + EnemyList.enemy_list.size() +"\nBullets : " + BulletList.bullet_list.size(), getSkin());
		debug.setX(15);
		debug.setY(ScreenCon.GAME_HEIGHT - debug.getHeight());
		addActor(debug);
	}
	
	@Override
	public void show() {
		super.show();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameLogic(delta);
		bulletLogic(delta);
		collisionLogic(delta);
		act(delta);
		draw();
		debug.setText("FPS : " + Gdx.graphics.getFramesPerSecond() +" \nEnemy : " + EnemyList.enemy_list.size() +"\nBullets : " + BulletList.bullet_list.size());
	}

	@Override
	public void resize(int width, int height) {
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
	
	public void gameLogic(float delta) {
		if (EnemyList.enemy_list.size() == 0) {
			for (int i = 0; i < r.nextInt(10); i++) {
				Enemy e = new Enemy(r.nextInt(4));
				addActor(e);
				EnemyList.enemy_list.add(e);
			}
		}
	}
	
	public void bulletLogic(float delta) {	
		if (Gdx.input.isTouched()) {
			if (System.currentTimeMillis() - LAST_SHOT >= FIRE_RATE) {
				Bullet b = new Bullet(ScreenCon.GAME_WIDTH/2, ScreenCon.GAME_HEIGHT/2, Gdx.input.getX(), Gdx.input.getY());
				addActor(b);
				BulletList.bullet_list.add(b);
	            LAST_SHOT = System.currentTimeMillis();
	        }
		}
	}
	
	public void collisionLogic(float delta) {
		for (int i = 0; i < BulletList.bullet_list.size(); i++) {
			for (int k = 0; k < EnemyList.enemy_list.size(); k++) {
				if (i <= BulletList.bullet_list.size() - 1)
				if (BulletList.bullet_list.get(i).isCollision(EnemyList.enemy_list.get(k))) {
					BulletList.bullet_list.get(i).dispose();
					EnemyList.enemy_list.get(k).removeHP();
					if (EnemyList.enemy_list.get(k).getHP() == 0)
						EnemyList.enemy_list.get(k).dispose();
				}
			}
		}
	}

}