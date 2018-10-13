package shadow;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class LoadingFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoadingFrame(){
		LoadingPanel loadingPanel = new LoadingPanel();
		SpringLayout springLayout = (SpringLayout) loadingPanel.getLayout();
		ImageIcon loading = new ImageIcon("C:\\Users\\52018588\\Videos\\Demo\\src\\images\\ajax-loader -big-circle.gif");
		JLabel label = new JLabel(loading, JLabel.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, label, 133, SpringLayout.NORTH, loadingPanel);
		springLayout.putConstraint(SpringLayout.EAST, label, -189, SpringLayout.EAST, loadingPanel);
		loadingPanel.add(label);
	   
	    this.setContentPane(loadingPanel);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(500, 400);
	    this.setResizable(false);
	    this.setVisible(true);
	}
	
	public static void main(String args[]){
		new LoadingFrame();
	}

}
