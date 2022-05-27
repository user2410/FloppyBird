package component.handler;

import java.util.LinkedList;
import java.util.Random;

import actor.Ground;
import actor.Tube;
import main.Game;

public class TubeHandler {

	private static Random random = new Random();
	
	public static LinkedList<Tube> tubes = new LinkedList<Tube>();
	
	public static int space = Game.HEIGHT - Ground.HEIGHT;
	public static int spacing = 120;
	public static int minSize = 40;
	public static int maxSize = space - spacing - minSize;
	public static int delay = 1; // in second
	public static int now;
	
	public static void spawnTubes() {
		int heightTop = minSize + random.nextInt(maxSize-minSize) + 1;
		
		int heightBottom = space - spacing - heightTop;
		
		Tube tubeTop	= new Tube(500, 0, 78, heightTop, Tube.Type.TOP);
		Tube tubeBottom	= new Tube(500, heightTop+spacing, 78, heightBottom, Tube.Type.BOTTOM);
		
		tubes.add(tubeTop);
		tubes.add(tubeBottom);
	}
	
	public static void tick() {
		if(now<delay) {
			now++;
		}else {
			spawnTubes();
			now = 0;
		}
	}
	
	public static void removeTube(Tube t) {
		tubes.remove(t);
	}
}
