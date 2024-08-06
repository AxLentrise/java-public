package entities;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import controllers.GenericListener;

public class MenuBar extends JMenuBar {
	
	public JMenu file;
	public JMenuItem read;
	public JMenuItem save;
	
	private GenericListener saveListener;
	private GenericListener readListener;
	
	public MenuBar() {
		
		file = new JMenu("File");
		file.setFont(getFont().deriveFont(13f));
		
		read = new JMenuItem("Read file");
		read.setFont(getFont().deriveFont(13f));
		
		save = new JMenuItem("Save file");
		save.setFont(getFont().deriveFont(13f));
		
		read.addActionListener(e -> readListener.action());
		save.addActionListener(e -> saveListener.action());
		
		file.add(read);
		file.add(save);
		add(file);		
	}
	
	public void setSaveListener(GenericListener listener) {
		saveListener = listener;
	}
	
	public void setReadListener(GenericListener listener) {
		readListener = listener;
	}
}
