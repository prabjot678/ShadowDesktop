package shadow;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class ForgotPasswordFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ForgotPasswordPanel forgotPasswordPanel;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JLabel lblCurrentPassword;
	private JLabel lblNewPassword;
	private JLabel lblConfirmPassword;
	private User user;
	
	public ForgotPasswordFrame(User user) {
		forgotPasswordPanel = new ForgotPasswordPanel();
		this.user = user;
		setupFrame(this);
	}
	
	private void setupFrame(ForgotPasswordFrame forgotPasswordFrame){
		this.setContentPane(forgotPasswordPanel);
		getContentPane().setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(173, 99, 148, 26);
		forgotPasswordPanel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(173, 147, 148, 26);
		forgotPasswordPanel.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(173, 194, 148, 26);
		forgotPasswordPanel.add(passwordField_2);
		
		lblCurrentPassword = new JLabel("Current password");
		lblCurrentPassword.setBounds(34, 105, 115, 14);
		forgotPasswordPanel.add(lblCurrentPassword);
		
		lblNewPassword = new JLabel("New password");
		lblNewPassword.setBounds(34, 153, 100, 14);
		forgotPasswordPanel.add(lblNewPassword);
		
		lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setBounds(34, 200, 115, 14);
		forgotPasswordPanel.add(lblConfirmPassword);
		
		JButton btnChnagePassword = new JButton("Change password");
		btnChnagePassword.setBounds(173, 251, 148, 23);
		btnChnagePassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
				changePasswordRequest.setOldPassword(passwordField.getPassword());
				changePasswordRequest.setNewPassword(passwordField_1.getPassword());
				changePasswordRequest.setConfirmPassword(passwordField_2.getPassword());
				CommonResponse commonResponse = ChangePassowrdRest.changePassword(user.getAuthenticationToken(), changePasswordRequest, forgotPasswordFrame);
				if(commonResponse.isSuccess()){
					
					
					
					int response = JOptionPane.showOptionDialog(forgotPasswordFrame, commonResponse.getMessage(),"Password update",JOptionPane.DEFAULT_OPTION,
					        JOptionPane.INFORMATION_MESSAGE, null, null, null);
					if(response == 0 || response == -1){
						forgotPasswordFrame.dispose();
						new HomeFrame(user);
					}
					
				} else {
					JOptionPane.showMessageDialog(forgotPasswordFrame, commonResponse.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		forgotPasswordPanel.add(btnChnagePassword);
		
		JLabel lblChangeYourPassword = new JLabel("Change your password before continue");
		lblChangeYourPassword.setForeground(Color.RED);
		lblChangeYourPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChangeYourPassword.setBounds(94, 33, 275, 26);
		forgotPasswordPanel.add(lblChangeYourPassword);
		this.pack();
		this.setSize(500, 400);
		FrameCommonProperties.centreWindow(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
