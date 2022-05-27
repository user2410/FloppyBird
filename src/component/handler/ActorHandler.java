package component.handler;

import java.awt.Graphics;
import java.util.LinkedList;

import actor.Actor;

public class ActorHandler {

	public static LinkedList<Actor> actors = new LinkedList<Actor>();
	
	public static void addActor(Actor a) {
		actors.add(a);
	}
	
	public static void removeActor(Actor a) {
		actors.remove(a);
	}
	
	public static void render(Graphics g) {
		for(int i=0; i<actors.size(); i++) {
			Actor actor = actors.get(i);
			actor.render(g);
		}
	}

	public static void tick() {
		// actors.forEach((a) -> a.tick());
		for(int i=0; i<actors.size(); i++) {
			Actor actor = actors.get(i);
			actor.tick();
		}
	}

}
