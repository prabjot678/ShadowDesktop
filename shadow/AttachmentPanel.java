package shadow;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class AttachmentPanel extends JPanel  {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private SpringLayout springLayout;
	private JTextArea area;
	
	AttachmentPanel(){
		
		setBackground(Color.WHITE);
		area = new JTextArea();
		springLayout = new SpringLayout();
		this.setLayout(springLayout);
		this.add(area);
	}

}
