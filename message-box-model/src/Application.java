import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Application extends JFrame {
	
	private static final int RIGHT = FlowLayout.RIGHT;
	private static final int LEFT = FlowLayout.LEFT;
	private static final Color DARK_GRAY = Color.DARK_GRAY;
	private static final String CENTER = BorderLayout.CENTER;
	private static final String NORTH = BorderLayout.NORTH;
	private static final int HORIZONTAL_SCROLLBAR_NEVER = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER;
	private static final int VERTICAL_SCROLLBAR_AS_NEEDED = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
	private static final int Y_AXIS = BoxLayout.Y_AXIS;
	
	private JMenuBar top;
	private JPanel mid;
	private int counter = 0;
	
	public Application() {
		super("Test Model");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(
			(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2,
			(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)
		);
		
		top = new JMenuBar();
		var menu = new JMenu("function");
		var item = new JMenuItem("generate");
		item.addActionListener(e -> new Thread(() -> generate()).start());
		menu.add(item);
		top.add(menu);
		
		mid = new JPanel();
		mid.setMinimumSize(new Dimension(
			(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4,
			(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4)
		);
		mid.setBorder(new EmptyBorder(0, 25, 10, 25));
		mid.setLayout(new BoxLayout(mid, Y_AXIS));
		
		var scrollPane = new JScrollPane(mid,
			VERTICAL_SCROLLBAR_AS_NEEDED,
			HORIZONTAL_SCROLLBAR_NEVER
		);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(
			(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/25
		);
		
		add(top, NORTH);
		add(scrollPane, CENTER);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	private void generate() {
		try {
			for(var i = 0; i <= 50; ++i) {
				// No código do app o lado seria definido
				// baseado se a mensagem foi enviada pelo
				// usuário ou outro cliente
				var side = counter%2 == 0 ? RIGHT : LEFT;
				var box = new JPanel(); // Nao sei qual seria o melhor forma de fazer isso
				box.setLayout(new FlowLayout(side));
				
				var label = new JLabel(">> Test Message " + counter++ + " <<");
				label.setBorder(new CompoundBorder(
					new LineBorder(DARK_GRAY, 1, true),
					new EmptyBorder(5, 5, 5, 5)
				));
				
				box.add(label);
				SwingUtilities.invokeLater(() -> mid.add(box));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		mid.updateUI();
	}
}
