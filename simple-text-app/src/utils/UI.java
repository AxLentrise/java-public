package utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

public class UI {

	public static KeyListener keyReleased(Consumer<KeyEvent> event) {
		return new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				event.accept(e);
			}
		};
	}
	
	public static KeyListener keyPressed(Consumer<KeyEvent> event) {
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				event.accept(e);
			}
		};
	}
}
