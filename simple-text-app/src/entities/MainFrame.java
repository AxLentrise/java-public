package entities;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import utils.Colors;
import utils.UI;

public class MainFrame extends JFrame {

	// file variables
	private String path = null;

	// swing objects
	private TextPanel textPanel;
	private MenuBar menuBar;
	private BottomPanel bottomPanel;

	public MainFrame() {
		// setting up default configurations
		super("Text Application");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Colors.BG_COLOR);
		
		// initializing objects
		textPanel = new TextPanel();
		bottomPanel = new BottomPanel();
		menuBar = new MenuBar();
		
		// adding objects and packing
		add(textPanel, BorderLayout.CENTER);
		add(menuBar, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);
		pack();
		
		// setting listeners
		menuBar.setReadListener(() -> readFile());
		menuBar.setSaveListener(() -> saveFile());
		textPanel.textArea.addKeyListener(UI.keyReleased(e -> updateCounter()));
		
		// setting default location to center of screen
		setLocationRelativeTo(null);
	}

	private void updateCounter() {
		new Thread(() -> bottomPanel.updateCounters(textPanel.getContent())).start();
	}

	private String getPath() {
		var fc = new JFileChooser();
		fc.setMultiSelectionEnabled(false);
		if (fc.showOpenDialog(MainFrame.this) != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		return fc.getSelectedFile().getAbsolutePath();
	}

	private void readFile() {
		if(bottomPanel.hasContent()) {
			new JOptionPane();
			if(0 == JOptionPane.showConfirmDialog(this,
					"Save current document before overwriting?",
					"Notice",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE)) saveFile();
		}
		
		var newPath = getPath();
		if (newPath == null) {
			return;
		}

		textPanel.clearTextArea();
		menuBar.file.setEnabled(false);
		
		new Thread(() -> {
			try (var br = new BufferedReader(new FileReader(newPath))) {
				var line = br.readLine();
				while (line != null) {
					textPanel.appendText(line + "\n");
					line = br.readLine();
				}
			} catch (IOException e) {
				System.err.println(e);
			}
			
			this.path = newPath;
			this.setTitle("Text Application - " + path);
			updateCounter();
			
			menuBar.file.setEnabled(true);
		}).start();
		
//		try {
//			for (var line : Files.readAllLines(Paths.get(newPath))) {
//			textPanel.appendText(line + "\n");
//		} catch (IOException e) {
//			System.err.println(e);
//		}
	}

	private void saveFile() {
		if (path == null) {
			var newPath = getPath();
			if (newPath == null) {
				return;
			}
			path = newPath;
		}
		
		menuBar.file.setEnabled(false);
		new Thread(() -> {
			try (var bw = new BufferedWriter(new FileWriter(path))) {
				bw.write(textPanel.getContent());
			} catch (IOException e) {
				System.err.println(e);
			}
			
			menuBar.file.setEnabled(true);
		}).start();
	}

}
