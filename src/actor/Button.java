package actor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Button extends Actor{

	private boolean pressed;
	
	private BufferedImage image;
	
	public Button(int x, int y, int width, int height, BufferedImage image) {
		super(x, y, width, height);
		this.image = image;
		pressed = false;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		if(image!=null) {
			if(pressed) {
				g.drawImage(image, x+1, y+1, width-2, height-2, null);
			}else {
				g.drawImage(image, x, y, null);
			}
		}else {
			g.setColor(Color.BLUE);
			if(pressed) {
				g.drawRect(x+1, y+1, width-2, height-2);
			}else {
				g.drawRect(x, y, width, height);
			}
		}
	}
	
	public void setPressed(boolean set) {
		pressed = set;
	}
	
	public boolean checkClicked(int mouseX, int mouseY) {
		if((mouseX >= x) && (mouseX <= x+width)
			&& (mouseY >= y) && (mouseY <= y+height)) {
			return true;
		}
		return false;
	}

}
