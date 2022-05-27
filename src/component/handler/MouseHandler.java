package component.handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Game;

public class MouseHandler implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// if(Button.check)
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(Game.replayBtn.checkClicked(e.getX(), e.getY())) {
			if(Game.gameOver) {
				Game.replayBtn.setPressed(true);
				ActorHandler.actors.clear();
				TubeHandler.tubes.clear();
				ActorHandler.addActor(Game.bird);
				ActorHandler.addActor(Game.ground);
				Game.gameOver = false;
				Game.score = 0;
				Game.replayBtn.setPressed(false);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

}
