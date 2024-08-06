package com.client.entities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.client.utils.Colors;

public class TopPanel extends JPanel {

	private static final int HEIGHT = 25;

	public JButton connect;
	private JButton close;
	private JButton minimize;

	private JPanel midPanel;
	private JLabel title;

	public TopPanel(Runnable closeFrame, Runnable minimizeFrame) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBackground(Colors.BG);
		setForeground(Colors.FG);
		setBorder(BorderFactory.createCompoundBorder( 	//
			BorderFactory.createMatteBorder(0, 0, 1, 0, Colors.BG_LN), //
			BorderFactory.createEmptyBorder(2, 5, 2, 5)	//
		));

		connect = new JButton("Login");
		connect.setIcon(getIcon("/resources/user-24-edit.png"));
		connect.setPreferredSize(new Dimension(75, HEIGHT));
		connect.setBorder(new EmptyBorder(2, 5, 2, 5));
		connect.setBackground(getBackground());
		connect.setForeground(getForeground());
		connect.addActionListener(e -> {
		});

		midPanel = new JPanel();
		midPanel.setBackground(getBackground());
		midPanel.setForeground(getForeground());
		var screen = Toolkit.getDefaultToolkit().getScreenSize();
		midPanel.setPreferredSize(new Dimension((int) screen.getWidth() / 2 - HEIGHT * 2, HEIGHT));

		title = new JLabel("Application name - TODO");
		title.setBorder(new EmptyBorder(2, 0, 0, 10));
		title.setBackground(getBackground());
		title.setForeground(getForeground());
		midPanel.add(title, BorderLayout.NORTH);

		close = new JButton();
		close.setIcon(getIcon("/resources/close-16-edit.png"));
		close.setPreferredSize(new Dimension(HEIGHT + 25, HEIGHT));
		close.setBorder(new EmptyBorder(2, 5, 2, 5));
		close.setBackground(getBackground());
		close.setForeground(getForeground());
		close.addActionListener(e -> closeFrame.run());

		minimize = new JButton();
		minimize.setIcon(getIcon("/resources/minus-24-edit.png"));
		minimize.setPreferredSize(new Dimension(HEIGHT + 25, HEIGHT));
		minimize.setBorder(new EmptyBorder(2, 5, 2, 5));
		minimize.setBackground(getBackground());
		minimize.setForeground(getForeground());
		minimize.addActionListener(e -> minimizeFrame.run());

		add(connect);
		add(midPanel);
		add(minimize);
		add(close);
	}

	public final ImageIcon getIcon(String path) {
		var url = getClass().getResource(path);
		if (url == null) {
			// TODO dialog error box
			System.err.println("Unable to load resource: " + path);
			return null;
		}
		return new ImageIcon(url);
	}
}
