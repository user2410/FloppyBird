package component.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;

public class KeyHandler implements KeyListener {

	public KeyHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			Game.bird.setVelY(-5);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
