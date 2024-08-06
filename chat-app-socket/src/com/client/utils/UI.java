package com.client.utils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.function.Consumer;

public abstract class UI {
	public static final MouseListener mousePressed(Consumer<MouseEvent> event) {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				event.accept(e);
			}
		};
	}
	
	public static final MouseListener mouseReleased(Consumer<MouseEvent> event) {
		return new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				event.accept(e);
			}
		};
	}
	
	public static final MouseMotionListener mouseDragged(Consumer<MouseEvent> event) {
		return new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				event.accept(e);
			}
		};
	}
}
