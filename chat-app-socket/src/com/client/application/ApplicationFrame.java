package com.client.application;

import java.awt.BorderLayout;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.client.entities.BottomPanel;
import com.client.entities.MessagePanel;
import com.client.entities.TopPanel;
import com.client.utils.UI;

public class ApplicationFrame extends JFrame {

	private static final String SERVER = "localhost";
	private static final int PORT = 8080;

	private volatile Socket socket;

	private static Point coord;

	private TopPanel topPanel;
	private MessagePanel messagePanel;
	private BottomPanel bottomPanel;
	private volatile OutputStreamWriter out;

	public ApplicationFrame(String title) {
		super(title);
		setLayout(new BorderLayout());
		setUndecorated(true);
		setResizable(true);

		topPanel = new TopPanel(() -> {
			dispose();
			System.exit(0);
		}, () -> {
			setState(JFrame.ICONIFIED);
		});
		messagePanel = new MessagePanel();
		bottomPanel = new BottomPanel();

		add(topPanel, BorderLayout.NORTH);
		add(messagePanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		topPanel.addMouseListener(UI.mousePressed(e -> coord = e.getPoint()));
		topPanel.addMouseListener(UI.mouseReleased(e -> coord = null));
		topPanel.addMouseMotionListener(UI.mouseDragged(e -> {
			var currCoord = e.getLocationOnScreen();
			setLocation(currCoord.x - coord.x, currCoord.y - coord.y);
		}));

		// default screen configurations
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);

		topPanel.connect.addActionListener(e -> connectToServer());
		bottomPanel.send.addActionListener(e -> sendToServer());
	}

	private void connectToServer() {
		// TODO dialog box for asking name
		topPanel.connect.setEnabled(false);
		new Thread(() -> {
			try (var socket = new Socket(SERVER, PORT)) {
				this.socket = socket;
				var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
				this.out = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
				try {
					var response = "";
					while ((response = in.readLine()) != null) {
						var responsef = response;
						SwingUtilities.invokeLater(() -> {
							messagePanel.textArea.append(responsef);
						});
					}
				} catch (IOException e) {
					SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, e.getMessage()));
				}
			} catch (IOException e) {
				SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, e.getMessage()));
			} finally {
				topPanel.connect.setEnabled(true);
			}
		}).start();
	}

	private void sendToServer() {
		if (socket == null) {
			JOptionPane.showMessageDialog(this, "Não foi conectado ainda");
			return;
		}
		var text = bottomPanel.input.getText();
		bottomPanel.input.setText("");
		new Thread(() -> {
			try {
				out.write(text + "\n");
				out.flush();
			} catch (IOException e) {
				SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, e.getMessage()));
			}
		}).start();
	}
}
