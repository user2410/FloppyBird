package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import actor.*;
import component.handler.ActorHandler;
import component.handler.KeyHandler;
import component.handler.MouseHandler;
import component.handler.TubeHandler;
import component.loader.GraphicLoader;
// import java.net.ServerSocket;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable{

	public static final int WIDTH	= 432;
	public static final int HEIGHT	= 768;
	
	public static boolean running;
	public static boolean gameOver;
	
	public static BufferedImage  background;
	public static BufferedImage  gameOverImg; // 288x63
	public static Bird bird;
	public static Ground ground;
	public static Button replayBtn;
	
	public static int score = 0;
	
	Thread	thread;
	// ServerSocket serverSock;
	
	public static void main(String[] args) {
		Game g = new Game();
		new Window("Floppy Bird", WIDTH, HEIGHT, g);
		g.start();
	}
	
	public synchronized void start() {
		running = true;
		thread	= new Thread();
		thread.start();
		run();
	}
	
	public void init() {
		addKeyListener(new KeyHandler());
		addMouseListener(new MouseHandler());
		
		background = GraphicLoader.load("background.png");
		gameOverImg = GraphicLoader.load("gameover.png");
		
		replayBtn = new Button((WIDTH-156)>>1, 200, 156, 87, GraphicLoader.load("playbutton.png"));
		bird = new Bird(150, 350, 51, 36);
		ground = new Ground();
		
		gameOver = false;
		score = 0;
	}
	
	public void tick() {
		if(!gameOver) {
			ActorHandler.tick();
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(background, 0, 0, null);
		
		ActorHandler.render(g);
		
		if(gameOver) {
			// draw gameover line
			g.drawImage(gameOverImg, (WIDTH-288)>>1, 130, null);
			// draw replay button
			Game.replayBtn.render(g);
		}
		
		// draw top white score
		g.setFont(new Font("Arial", Font.BOLD, 48));
		g.setColor(Color.WHITE);
		
		String s = Integer.toString(score);
		int textWidth = g.getFontMetrics().stringWidth(s);
		
		g.drawString(s, (Game.WIDTH-textWidth)>>1, 50);
		
		g.dispose();
		bs.show();
	}

	@Override
	public void run() {
		init();
		this.requestFocus();
		
		long pastTime		 = System.nanoTime();
		double amountOfTicks = 60.0;
		double deltaTicks	 = 0.0;
		long timer			 = System.currentTimeMillis();
		
		int updates	= 0;
		int frames	= 0;
		
		while(running) {
			long now	= System.nanoTime();
			deltaTicks	+= (now - pastTime) / 1e9 * amountOfTicks;
			pastTime	= now;
			
			while(deltaTicks > 0.0) {
				tick();
				updates++;
				
				render();
				frames++;
				
				deltaTicks--;
			}
			
			// if 1s passed
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("Current FPS: " + frames + " | Updates: " + updates);
				TubeHandler.tick();
				updates = 0;
				frames = 0;
			}
		}
	}

}
