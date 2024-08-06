package application;
import javax.swing.SwingUtilities;

import entities.MainFrame;

public class Application {
	public static void main(String [] args) {
		SwingUtilities.invokeLater(() -> {
			MainFrame frame = new MainFrame();
			frame.setVisible(true);
		});
	}
}
