package shadow;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import javax.swing.ImageIcon;

public class LoginFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginPanel loginPanel;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblPassword;
	private JLabel lblLogin;
	private JButton btnLogin;
	private JButton btnSignup;
	private JLabel lblNewLabel;

	public LoginFrame() {
		loginPanel = new LoginPanel();

		setupFrame(this);
	}

	private void setupFrame(LoginFrame loginFrame) {
		this.setContentPane(loginPanel);
		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(169, 120, 130, 20);
		loginPanel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(169, 169, 130, 20);
		loginPanel.add(textField_1);
		textField_1.setColumns(10);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(93, 218, 83, 23);
		btnLogin.addActionListener(this);
		loginPanel.add(btnLogin);

		btnSignup = new JButton("Signup");
		btnSignup.setBounds(226, 218, 73, 23);
		btnSignup.addActionListener(new SingupButtonListener(this));
		loginPanel.add(btnSignup);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(93, 123, 62, 14);
		loginPanel.add(lblUsername);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(93, 172, 66, 14);
		loginPanel.add(lblPassword);

		lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogin.setBounds(185, 67, 83, 14);
		loginPanel.add(lblLogin);

		JLabel lblForgotPassword = new JLabel("<HTML><U>Forgot Password?</U></HTML>");
		lblForgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblForgotPassword.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String email = JOptionPane.showInputDialog("Enter email");
				if (StringUtils.isBlank(email)) {
					return;
				}
				CommonResponse commonResponse = ChangePassowrdRest.forgotPassword(email, loginFrame);
				if (commonResponse != null && commonResponse.isSuccess()) {
					JOptionPane.showOptionDialog(loginFrame, commonResponse.getMessage(), "Forgot password",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				}

			}
		});
		lblForgotPassword.setForeground(Color.RED);
		lblForgotPassword.setBounds(309, 172, 114, 14);
		loginPanel.add(lblForgotPassword);
		
		lblNewLabel = new JLabel("New label");
		//lblNewLabel.setIcon(new ImageIcon("C:\\Users\\52018588\\Downloads\\ajax-loader (3).gif"));
		lblNewLabel.setBounds(207, 294, 46, 14);
		loginPanel.add(lblNewLabel);
		//this.pack();
		this.setSize(500, 400);
		FrameCommonProperties.centreWindow(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		
		
		lblNewLabel.setText("I am changed");
		this.lblNewLabel.setVisible(true);
		
		try {
			
			btnLogin.setEnabled(false);
			btnSignup.setEnabled(false);
			
			
			
			HttpPost postRequest = new HttpPost(APIConstant.API_BASE_URL + "/login");

			JSONObject request = new JSONObject();
			request.put("username", textField.getText());
			request.put("password", textField_1.getText());

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			StringEntity input = new StringEntity(request.toString());

			postRequest.addHeader("content-type", "application/json");
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);
			//lblNewLabel.setVisible(false);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			StringBuilder sb = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			System.out.println("Output from Server .... \n" + sb.toString());

			JSONObject httpResponse = new JSONObject(sb.toString());

			if (!(boolean) httpResponse.get("success")) {
				JOptionPane.showMessageDialog(this, "Incorrect login or password", "Error", JOptionPane.ERROR_MESSAGE);
				btnLogin.setEnabled(true);
				btnSignup.setEnabled(true);
				return;
			}

			User user = new User();
			user.setFirstName(httpResponse.getString("firstName"));
			user.setLastName(httpResponse.getString("lastName"));
			user.setAuthenticationToken(httpResponse.getString("authenticationToken"));
			boolean passwordNeedsToChange = httpResponse.getBoolean("passwordNeedsToChange");
			httpClient.getConnectionManager().shutdown();
			this.dispose();
			if (passwordNeedsToChange) {
				new ForgotPasswordFrame(user);
			} else {
				new HomeFrame(user);
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (HttpHostConnectException httpHostConnectException) {
			JOptionPane.showMessageDialog(this, "Server seems to be down, Try after some time !!!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnLogin.setEnabled(true);
		btnSignup.setEnabled(true);

	}

	public static void main(String args[]) {
		new LoginFrame();
	}
}
