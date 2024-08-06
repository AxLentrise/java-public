package com.client.entities;

import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.client.utils.Colors;
import com.client.utils.GBC;

public class BottomPanel extends JPanel {

	private JLabel status;
	public JTextField input;
	public JButton send;

	public BottomPanel() {
		setLayout(new GridBagLayout());
		setBorder(new CompoundBorder(	//
			BorderFactory.createMatteBorder(1,  0,  0,  0, Colors.BG_LN), //
			new EmptyBorder(2, 5, 2, 5)	//
		));
		setBackground(Colors.BG);
		setForeground(Colors.FG);

		status = new JLabel("Disconnected");
		status.setBorder(new EmptyBorder(5, 5, 5, 5));
		status.setForeground(Colors.FG);
		add(status, new GBC(0, 0));

		input = new JTextField();
		input.setBorder(new CompoundBorder(new LineBorder(Colors.BG2), new EmptyBorder(2, 10, 2, 5)));
		input.setBackground(getBackground());
		input.setForeground(getForeground());
		input.setCaretColor(getForeground());
		add(input, new GBC(1, 0).horizontal().left(5).right(5));

		send = new JButton();
		send.setIcon(getIcon("/resources/send-24-edit.png"));
		send.setBorder(new EmptyBorder(2, 5, 2, 5));
		send.setBackground(getBackground());
		send.setForeground(getForeground());
		add(send, new GBC(2, 0));
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
