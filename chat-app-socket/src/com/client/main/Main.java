package com.client.main;

import javax.swing.SwingUtilities;

import com.client.application.ApplicationFrame;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new ApplicationFrame("Chat Application").setVisible(true));
	}
}
