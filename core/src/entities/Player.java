package entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import constants.ScreenCon;

public class Player extends Actor {

	private ShapeRenderer shape = new ShapeRenderer();
	Circle body = new Circle();

	public Player() {
		setWidth(32);
		setHeight(32);
		setX((ScreenCon.GAME_WIDTH/2) - (getWidth()/2));
		setY((ScreenCon.GAME_HEIGHT/2) - (getHeight()/2));
		body.setX(getX());
		body.setY(getY());
		//body.setRadius(getWidth()/2);
	}
	
	@Override
	public void act(float delta) {
	
	}
	
	@Override
	public void draw(Batch bat, float parentAlpha) {
		bat.end();
		
		shape.setProjectionMatrix(bat.getProjectionMatrix());
		shape.setTransformMatrix(bat.getTransformMatrix());
		shape.translate(getX(), getY(), 0);
		
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.BLUE);
		shape.rect(0, 0, getWidth(), getHeight());
		shape.end();
		
		bat.begin();
	}
	
	public Circle getBody() {
		return body;
	}
	
	public boolean isCollision(Circle c) {
		if (body.contains(c))
			return true;
		return false;
	}

}
