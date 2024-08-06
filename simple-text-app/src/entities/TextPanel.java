package entities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class TextPanel extends JPanel {
	
	public JTextArea textArea;
	
	public TextPanel() {
		textArea = new JTextArea();
		textArea.setLineWrap(true);
				
		setLayout(new BorderLayout());
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		
		var dimension = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		setPreferredSize(new Dimension((int)dimension.getWidth()/2, (int)dimension.getHeight()/2));
		
		// simple customizations
		textArea.setBorder(new EmptyBorder(5, 10, 0, 10));
		textArea.setFont(getFont().deriveFont(18f));
	}
	
	public void appendText(String text) {
		textArea.append(text);
	}
	
	public String getContent() {
		return textArea.getText();
	}
	
	public void clearTextArea() {
		textArea.setText(null);
	}
}
