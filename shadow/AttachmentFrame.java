package shadow;

import javax.swing.JFrame;

public class AttachmentFrame extends JFrame {
	
	
	private AttachmentPanel attachmentPanel;
	
	public AttachmentFrame() {
		attachmentPanel = new AttachmentPanel();
		setupFrame(this);
	}
	
	private void setupFrame(AttachmentFrame attachmentFrame){
		this.setContentPane(attachmentPanel);
		getContentPane().setLayout(null);
		this.setSize(500, 400);
		FrameCommonProperties.centreWindow(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public static void main(String args[]){
		
		
		
	}

}
