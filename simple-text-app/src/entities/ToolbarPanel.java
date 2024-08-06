//package entities;
//
//import javax.swing.JButton;
//import javax.swing.JTextField;
//import javax.swing.JToolBar;
//
//import controllers.GenericListener;
//
//public class ToolbarPanel extends JToolBar {
//	
//	private JTextField txtField;
//	
//	private JButton readBtn;
//	private JButton saveBtn;
//	
//	private GenericListener saveListener;
//	private GenericListener readListener;
//	
//	public ToolbarPanel() {
//				
//		txtField = new JTextField(10);
//		txtField.setEnabled(false);
//		
//		saveBtn = new JButton("Save");
//		readBtn = new JButton("Read");
//		
//		readBtn.addActionListener(e -> {
//			if(readListener != null) {
//				readListener.action();
//			}
//		});
//		
//		saveBtn.addActionListener(e -> {
//			if(saveListener != null) {
//				saveListener.action();
//			}
//		});
//		
//		add(readBtn);
//		add(txtField);
//		add(saveBtn);
//		
//	}
//	
//	public String getText() {
//		return txtField.getText();
//	}
//	
//	public void setSaveListener(GenericListener listener) {
//		saveListener = listener;
//	}
//	
//	public void setReadListener(GenericListener listener) {
//		readListener = listener;
//	}
//}
