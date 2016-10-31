package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import constants.BulletList;
import constants.ScreenCon;
import entities.Enemy;

public class Bullet extends Actor {
	
	private ShapeRenderer shape = new ShapeRenderer();
	private float newX, newY;
	private Vector2 position, velocity;
	private float speed = 3.5f;
	Circle body = new Circle();

	public Bullet(float startX, float startY, float desX, float desY) {
		setWidth(8);
		setHeight(8);
		setX(startX);
		setY(startY);
		body.setRadius(getWidth()/2);
		body.setX(getX() + body.radius);
		body.setY(getY() + body.radius);
		position = new Vector2(getX(), getY());
		velocity = new Vector2(0, 0);
		desY = Gdx.graphics.getHeight() - desY;
		newX = (float) ((desX - getX()) / Math.sqrt(((desX - getX()) * (desX - getX())) + ((desY - getY()) * (desY - getY()))));
		newY = (float) ((desY - getY()) / Math.sqrt(((desY - getY()) * (desY - getY())) + ((desX - getX()) * (desX - getX()))));
		velocity.set(newX * speed, newY * speed);
		//velocity.set(desX - getX(), desY - getY());
	}
	
	@Override
	public void act(float delta) {
		position.add(velocity.x, velocity.y);
		setX(position.x);
		setY(position.y);
		if (getX() > ScreenCon.GAME_WIDTH) {
			dispose();
			BulletList.bullet_list.remove(this);
		}
		if (getX() < 0) {
			dispose();
			BulletList.bullet_list.remove(this);
		}
		if (getY() > ScreenCon.GAME_HEIGHT) {
			dispose();
			BulletList.bullet_list.remove(this);
		}
		if (getY() < 0) {
			dispose();
			BulletList.bullet_list.remove(this);
		}
	}
	
	@Override
	public void draw(Batch bat, float parentAlpha) {
		bat.end();
		
		shape.setProjectionMatrix(bat.getProjectionMatrix());
		shape.setTransformMatrix(bat.getTransformMatrix());
		//shape.translate(getX(), getY(), 0);
		
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.GOLD);
		body.setX(getX() + body.radius);
		body.setY(getY() + body.radius);
		shape.circle(body.x, body.y, body.radius);
		shape.end();
		
		bat.begin();
	}
	
	public Circle getBody() {
		return body;
	}
	
	public boolean isCollision(Enemy e) {
		if (getX() >= e.getX() - (e.getWidth()/2) && getX() <= e.getX() + e.getWidth()
				&& getY() >= e.getY() - (e.getHeight()/2) && getY() <= e.getY() + e.getHeight()) {
			return Intersector.overlaps(body, e.getBody());
	    }
		return false;
	}
	
	public void dispose() {
		shape.dispose();
		BulletList.bullet_list.remove(this);
		this.remove();
	}
	
}
