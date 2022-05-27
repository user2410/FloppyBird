package component.loader;

import java.io.InputStream;

public class ResourceLoader {

	public static InputStream load(String path) {
		InputStream inpStream = null;
		/*
		Class cls = Class.forName("ResourceLoader");
		// return the ClassLoader object associated with this Class
		ClassLoader cLoader = cls.getClassLoader();
		inp = cLoader.getResourceAsStream(path);
		*/
		inpStream = ResourceLoader.class.getResourceAsStream(path);
		if(inpStream == null)
			inpStream = ResourceLoader.class.getResourceAsStream("/" + path);
			
		return inpStream;
	}

}
