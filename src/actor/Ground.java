package actor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import component.handler.ActorHandler;
import component.loader.GraphicLoader;
import main.Game;

public class Ground extends Actor{
	public static int HEIGHT = 168;
	
	// 2 image offsets
	// |1|2|3|4| => |2|3|4|1|
	private int x1, x2;
	
	private BufferedImage image;

	public Ground() {
		super(0, Game.HEIGHT - Ground.HEIGHT, Game.WIDTH, Ground.HEIGHT);
		
		x1 = 0;
		x2 = Game.WIDTH;
		
		this.velX = 3.0f;
		
		image = GraphicLoader.load("ground.png");
		
		ActorHandler.addActor(this);
	}

	@Override
	public void tick() {
		x1 -= velX;
		x2 -= velX;
		
		if(x1 + Game.WIDTH < 0) {
			x1 = Game.WIDTH;
		}
		
		if(x2 + Game.WIDTH < 0) {
			x2 = Game.WIDTH;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, x1, Game.HEIGHT - Ground.HEIGHT, null);
		g.drawImage(image, x2, Game.HEIGHT - Ground.HEIGHT, null);
	}

}
