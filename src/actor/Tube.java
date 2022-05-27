package actor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import component.handler.ActorHandler;
import component.handler.TubeHandler;
import component.loader.GraphicLoader;

public class Tube extends Actor{

	public enum Type{
		TOP, BOTTOM
	};
	
	Type type;
	
	BufferedImage tubeBody; // 72x444
	BufferedImage tubeCap;	// 78x36
	
	private boolean isPassed;
	
	public Tube(int x, int y, int width, int height, Type type) {
		super(x, y, width, height);
		
		this.type = type;
		this.velX = 3.0f;
		
		tubeBody = GraphicLoader.load("tube.png");
		
		if(this.type == Type.BOTTOM) {
			tubeCap = GraphicLoader.load("tubebottomdown.png");
		}else {
			tubeCap = GraphicLoader.load("tubebottomtop.png");			
		}
		
		ActorHandler.addActor(this);
		
		isPassed = false;
	}

	@Override
	public void tick() {
		x -= velX;
		
		// off screen tube pair
		if(x < (-width-3) && isPassed) {
			ActorHandler.removeActor(this);
			TubeHandler.removeTube(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(tubeBody, x, y, 72, height, null);
		if(type == Type.BOTTOM) {
			g.drawImage(tubeCap, x-3, y, null);
		}else {
			g.drawImage(tubeCap, x-3, y+height-36, null);			
		}
	}
	
	public boolean isPassed() {
		return isPassed;
	}

	public void setPassed() {
		this.isPassed = true;
	}
}
