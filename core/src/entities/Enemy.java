package entities;

import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import constants.EnemyList;
import constants.ScreenCon;
import handlers.ScreenManager;

public class Enemy extends Actor {
	
	private NinePatchDrawable hpBarBackground;
	private NinePatchDrawable hpBar;
	private ShapeRenderer shape = new ShapeRenderer();
	private int id, hp, max_hp;
	private Vector2 position, velocity;
	private float speed, desX, desY, newX, newY;
	MoveToAction move = new MoveToAction();
	AssetManager am;
	Random r = new Random();
	Circle body = new Circle();

	public Enemy(int id) {
		this.id = id;
		setWidth(32);
		setHeight(32);
		am = ScreenManager.getInstance().getManager();
		TextureAtlas skinAtlas = am.get("data/uiskin.atlas", TextureAtlas.class);
		NinePatch hpBarBackgroundPatch = new NinePatch(skinAtlas.findRegion("default-round"), 5, 5, 4, 4);
		NinePatch hpBarPatch = new NinePatch(skinAtlas.findRegion("default-round-down"), 5, 5, 4, 4);
		hpBar = new NinePatchDrawable(hpBarPatch);
		hpBarBackground = new NinePatchDrawable(hpBarBackgroundPatch);
		int spawn_r = r.nextInt(4);
		randomizedStuff(spawn_r);
		body.setRadius(getWidth()/2);
		body.setX(getX() + body.radius);
		body.setY(getY() + body.radius);
		position = new Vector2(getX(), getY());
		velocity = new Vector2(0, 0);
		//move.setPosition((ScreenCon.GAME_WIDTH/2) - (getWidth()/2), (ScreenCon.GAME_HEIGHT/2) - (getHeight()/2));
		//move.setDuration(15);
		this.addAction(move);
	}
	
	@Override
	public void draw(Batch bat, float parentAlpha) {
		bat.end();
		
		shape.setProjectionMatrix(bat.getProjectionMatrix());
		shape.setTransformMatrix(bat.getTransformMatrix());
		shape.begin(ShapeType.Filled);
		switch(id) {
		case 1:	shape.setColor(Color.RED);
			break;
		case 2: shape.setColor(Color.YELLOW);
			break;
		case 3: shape.setColor(Color.GREEN);
			break;
		case 4: shape.setColor(Color.FIREBRICK);
			break;
		}
		body.setX(getX() + body.radius);
		body.setY(getY() + body.radius);
		shape.circle(body.x, body.y, body.radius);
		shape.end();
		
		bat.begin();
		
		int size = (int) getWidth();
		float hpbar_size = 0;
		switch(size) {
		case 8:
			hpbar_size = 16;
			break;
		case 16:
			hpbar_size = 16;
			break;
		case 32:
			hpbar_size = 32;
			break;
		case 64:
			hpbar_size = 32;
			break;
		case 96:
			hpbar_size = 32;
			break;
		}
		hpBarBackground.draw(bat, getX() - (getWidth()/2), getY() + getHeight() + 10, max_hp * hpbar_size * getScaleX(), 5 * getScaleY());
	    hpBar.draw(bat, getX() - (getWidth()/2), getY() + getHeight() + 10, hp * hpbar_size * getScaleX(), 5 * getScaleY());
		desX = (ScreenCon.GAME_WIDTH - getWidth()) / 2;
		desY = (ScreenCon.GAME_HEIGHT - getHeight()) / 2;
		newX = (float) ((desX - getX()) / Math.sqrt(((desX - getX()) * (desX - getX())) + ((desY - getY()) * (desY - getY()))));
		newY = (float) ((desY - getY()) / Math.sqrt(((desY - getY()) * (desY - getY())) + ((desX - getX()) * (desX - getX()))));
		velocity.set(newX * speed, newY * speed);
	    position.add(velocity.x, velocity.y);
		setX(position.x);
		setY(position.y);
	}
	
	public void randomizedStuff(int spawn_r) {
		switch(spawn_r) {
		case 0:
			setX(r.nextInt(ScreenCon.GAME_WIDTH));
			setY(r.nextInt(ScreenCon.GAME_HEIGHT));
		case 1:
			setX(0);
			setY(r.nextInt(ScreenCon.GAME_HEIGHT));
			break;
		case 2:
			setX(r.nextInt(ScreenCon.GAME_WIDTH));
			setY(0);
			break;
		case 3: 
			setX(ScreenCon.GAME_WIDTH);
			setY(r.nextInt(ScreenCon.GAME_HEIGHT));
			break;
		case 4:
			setX(r.nextInt(ScreenCon.GAME_WIDTH));
			setY(ScreenCon.GAME_HEIGHT);
			break;
		}
		switch(spawn_r) {
		case 0:
			setWidth(32);
			setHeight(32);
			hp = 3;
			max_hp = 3;
			speed = 1.0f;
		case 1:
			setWidth(64);
			setHeight(64);
			hp = 4;
			max_hp = 4;
			speed = 0.5f;
			break;
		case 2:
			setWidth(16);
			setHeight(16);
			hp = 2;
			max_hp = 2;
			speed = 2.0f;
			break;
		case 3: 
			setWidth(8);
			setHeight(8);
			hp = 1;
			max_hp = 1;
			speed = 3.0f;
			break;
		case 4:
			setWidth(96);
			setHeight(96);
			hp = 5;
			max_hp = 8;
			speed = 0.2f;
			break;
		}
	}
	
	public void removeHP() {
		hp--;
	}
	
	public Circle getBody() {
		return body;
	}
	
	public int getHP() {
		return hp;
	}
	
	public void dispose() {
		shape.dispose();
		EnemyList.enemy_list.remove(this);
		this.remove();
	}

}
