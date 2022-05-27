package component.loader;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicLoader {

	public static BufferedImage load(String path) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(ResourceLoader.load("/"+path));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}

}
