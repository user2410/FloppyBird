package actor;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Actor {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected float velX;
	protected float velY;

	public Actor(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width	= width;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getVelX() {
		return velX;
	}
	
	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}
	
	public void setVelY(float velY) {
		this.velY = velY;
	}
}
