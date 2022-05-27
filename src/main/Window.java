package main;

import java.awt.Insets;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {

	public Window(String title, int width, int height, Game game) {
		// Resize the window
		setTitle(title);
		pack();
		Insets insets = getInsets();
		setSize(width + insets.right + insets.left,
				height + insets.top + insets.bottom);
		
		setLocationRelativeTo(null);	// Center the window
		setResizable(false);			// Disable resizing
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
		add(game);
	}

}
