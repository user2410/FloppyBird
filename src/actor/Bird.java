package actor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.NoSuchElementException;

import component.Animation;
import component.handler.ActorHandler;
import component.handler.TubeHandler;
import component.loader.GraphicLoader;
import main.Game;

public class Bird extends Actor {
	
	Animation animComponent;
	
	private float gravity; // gravitational acceleration
	private float maxSpeed;

	public Bird(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		BufferedImage[] images = new BufferedImage[3];
		
		gravity = 0.3f;
		maxSpeed = 12.0f;
		
		for(int i=0; i<images.length; i++) {
			images[i] = GraphicLoader.load("Bird" + i + ".png");
		}
		animComponent = new Animation(this, 100, true, images);
		animComponent.start();
		
		ActorHandler.addActor(this);
	}

	@Override
	public void tick() {
		velY += gravity;
		y += velY;
		
		if(velY > maxSpeed) {
			velY = maxSpeed;
		}
		
		// Not fall below ground
		if(y+height > Game.HEIGHT-166) {
			y = Game.HEIGHT-166-height;
			this.velY = 0.0f;
		}
		
		// Not fly too high
		if(y<0) {
			y = 0;
			this.velY = 0.0f;
		}
		
		// check for collision with tubes
		Tube tube = null;
		for(int i=0; i<TubeHandler.tubes.size(); i++) {
			tube = TubeHandler.tubes.get(i);
			if(this.getBounds().intersects(tube.getBounds())) {
				Game.gameOver = true;
			}
		}
		
		// check passed pair of tubes
		// first pair
		try {
    		Tube tube00 = TubeHandler.tubes.getFirst(); Tube tube01 = TubeHandler.tubes.get(1);
    		if(tube00.getX() < x && !tube00.isPassed()) {
    			tube00.setPassed();
    			tube01.setPassed();
    			Game.score++;
    		}
		}catch(NoSuchElementException exp) {}
		
		animComponent.tick();
	}

	@Override
	public void render(Graphics g) {
		animComponent.render(g);
	}

}
