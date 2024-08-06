package com.client.entities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.client.utils.Colors;

public class MessagePanel extends JPanel {

	public JTextArea textArea;

	public MessagePanel() {
		setLayout(new BorderLayout());

		textArea = new JTextArea();
		var screen = Toolkit.getDefaultToolkit().getScreenSize();
		textArea.setPreferredSize(new Dimension((int) screen.getWidth() / 2, (int) screen.getHeight() / 2));
		textArea.setBorder(new EmptyBorder(5, 10, 5, 10));
		textArea.setBackground(Colors.BG2);
		textArea.setForeground(Colors.FG);
		textArea.setLayout(new BoxLayout(textArea, BoxLayout.Y_AXIS));
		textArea.setCaretColor(getForeground());
		textArea.setLineWrap(true);
		textArea.setFont(textArea.getFont().deriveFont(19f));

		var scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		add(scrollPane, BorderLayout.CENTER);
	}
}
