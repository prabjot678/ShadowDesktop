package shadow;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;


public class HomePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SpringLayout springLayout;
	private JTextArea area;
	public HomePanel() {
		setBackground(Color.WHITE);
		area = new JTextArea();
		springLayout = new SpringLayout();
		setupPanel();
	}
	
	private void setupPanel(){
		this.setLayout(springLayout);
		this.add(area);
	}
}
