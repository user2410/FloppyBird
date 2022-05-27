package component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import actor.Actor;

public class Animation {

	private Actor owner;
	
	private int x;
	private int y;
	
	private long delay;		// in milisecond
	private long startTime;
	
	private boolean loop;
	private boolean running;
	
	private BufferedImage[] images;
	private int currentImage;
	
	public Animation(long delay, boolean loop, BufferedImage[] images) {
		this.currentImage	= 0;
		this.delay			= delay;
		this.startTime		= 0;
		this.loop			= loop;
		this.images			= images;		
	}
	
	public Animation(Actor owner, long delay, boolean loop, BufferedImage[] images) {
		this(delay, loop, images);
		this.x = owner.getX();
		this.y = owner.getY();
		this.setOwner(owner);
	}

	public Animation(Actor owner, int x, int y, long delay, boolean loop, BufferedImage[] images) {
		this(owner, delay, loop, images);
		this.x += x;
		this.y += y;
	}
	
	public Animation(int x, int y, long delay, boolean loop, BufferedImage[] images) {
		this(delay, loop, images);
		this.x = x;
		this.y = y;
		this.setOwner(null);
	}
	
	public void start() {
		this.running	= true;
		this.currentImage = 0;
		this.startTime	= 0;
	}
	
	public void stop() {
		this.running	= false;
		this.currentImage = 0;
		this.startTime	= 0;
	}
	
	public void render(Graphics g) {
		if(owner == null) {
			g.drawImage(images[currentImage], x, y, null);
		}else {
			g.drawImage(images[currentImage], owner.getX(), owner.getY(), null);
		}
	}
	
	public void tick() {
		long pastTime = (long) ((System.nanoTime() - startTime) / 1e6);
		
		if(pastTime >= delay && running) {
			currentImage++;
			startTime = System.nanoTime();
		}
		
		if(currentImage == images.length) {
			currentImage = 0;
			if(!loop) stop();
		}
	}
	
	public Actor getOwner() {
		return owner;
	}
	
	public void setOwner(Actor owner) {
		this.owner = owner;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public BufferedImage[] getImages() {
		return images;
	}

	public void setImages(BufferedImage[] images) {
		this.images = images;
	}
	
}
