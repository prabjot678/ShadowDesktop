package shadow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SingupButtonListener implements ActionListener {
	
	
	 LoginFrame  loginFrame;
	 
	 public SingupButtonListener(LoginFrame loginFrame){
		 this.loginFrame = loginFrame;
	 }
	 

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		loginFrame.dispose();
		new SignupFrame();
	}

}
