package entities;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BottomPanel extends JPanel {

	private JLabel charCounter;
	private JLabel wordCounter;

	private int numberChars = 0;
	private int numberWords = 0;

	public BottomPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		charCounter = new JLabel("Characters: 0");
		charCounter.setFont(getFont().deriveFont(13f));
		charCounter.setBorder(new EmptyBorder(5, 2, 2, 5));
		
		wordCounter = new JLabel("Words: 0");
		wordCounter.setFont(getFont().deriveFont(13f));
		wordCounter.setBorder(new EmptyBorder(5, 2, 2, 5));
		
		add(charCounter);
		add(wordCounter);
	}

	public void updateCounters(String text) {
		var words = new ArrayList<String>();
		for (var n : text.split(" ")) {
			if (n.length() >= 2) {
				words.add(n);
			}
		}

		numberChars = text.length();
		numberWords = words.size();

		charCounter.setText("Characters: " + numberChars);
		wordCounter.setText("Words: " + numberWords);
	}
	
	public boolean hasContent() {
		return (numberChars != 0);
	}
}
