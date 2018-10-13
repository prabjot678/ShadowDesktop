package shadow;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import listener.LoginButtonListener;

public class SignupFrame extends JFrame implements ActionListener {
	
	private SignupPanel signupPanel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField txtPrabhkahlongmailcom;
	private JLabel lblEnterFirstname;
	private JLabel lblEnterLastName;
	private JLabel lblEnterUsername;
	private JLabel lblEnterEmail;
	private JLabel lblEnterPassword;
	
	public SignupFrame() {
		signupPanel = new SignupPanel();
		SpringLayout springLayout = (SpringLayout) signupPanel.getLayout();
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 79, SpringLayout.NORTH, signupPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, 107, SpringLayout.NORTH, signupPanel);
		springLayout.putConstraint(SpringLayout.EAST, textField, -162, SpringLayout.EAST, signupPanel);
		signupPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First name");
		springLayout.putConstraint(SpringLayout.WEST, textField, 49, SpringLayout.EAST, lblFirstName);
		springLayout.putConstraint(SpringLayout.SOUTH, lblFirstName, -366, SpringLayout.SOUTH, signupPanel);
		springLayout.putConstraint(SpringLayout.EAST, lblFirstName, -384, SpringLayout.EAST, signupPanel);
		signupPanel.add(lblFirstName);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, 23, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_1, -162, SpringLayout.EAST, signupPanel);
		signupPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last name");
		springLayout.putConstraint(SpringLayout.NORTH, lblLastName, 37, SpringLayout.SOUTH, lblFirstName);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 50, SpringLayout.EAST, lblLastName);
		springLayout.putConstraint(SpringLayout.WEST, lblLastName, 0, SpringLayout.WEST, lblFirstName);
		signupPanel.add(lblLastName);
		
		textField_2 = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, textField_1, -34, SpringLayout.NORTH, textField_2);
		springLayout.putConstraint(SpringLayout.WEST, textField_2, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.EAST, textField_2, -162, SpringLayout.EAST, signupPanel);
		signupPanel.add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, passwordField, 325, SpringLayout.NORTH, signupPanel);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, textField);
		signupPanel.add(passwordField);
		
		JLabel lblUsername = new JLabel("Username");
		springLayout.putConstraint(SpringLayout.NORTH, lblUsername, 42, SpringLayout.SOUTH, lblLastName);
		springLayout.putConstraint(SpringLayout.WEST, lblUsername, 0, SpringLayout.WEST, lblFirstName);
		signupPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 7, SpringLayout.NORTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblFirstName);
		signupPanel.add(lblPassword);
		
		JButton btnSubmit = new JButton("Submit");
		springLayout.putConstraint(SpringLayout.WEST, btnSubmit, 107, SpringLayout.WEST, signupPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, btnSubmit, -66, SpringLayout.SOUTH, signupPanel);
		btnSubmit.addActionListener(this);
		signupPanel.add(btnSubmit);
		
		JLabel lblSignup = new JLabel("Signup");
		springLayout.putConstraint(SpringLayout.NORTH, lblSignup, 23, SpringLayout.NORTH, signupPanel);
		springLayout.putConstraint(SpringLayout.WEST, lblSignup, 170, SpringLayout.WEST, signupPanel);
		lblSignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignup.setFont(new Font("Tahoma", Font.PLAIN, 16));
		signupPanel.add(lblSignup);
		
		JButton btnLogin = new JButton("Login");
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 0, SpringLayout.NORTH, btnSubmit);
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 55, SpringLayout.EAST, btnSubmit);
		btnLogin.addActionListener(new LoginButtonListener(this));
		signupPanel.add(btnLogin);
		
		txtPrabhkahlongmailcom = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_2, -51, SpringLayout.NORTH, txtPrabhkahlongmailcom);
		springLayout.putConstraint(SpringLayout.SOUTH, textField_2, -23, SpringLayout.NORTH, txtPrabhkahlongmailcom);
		springLayout.putConstraint(SpringLayout.NORTH, txtPrabhkahlongmailcom, 243, SpringLayout.NORTH, signupPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, txtPrabhkahlongmailcom, -191, SpringLayout.SOUTH, signupPanel);
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 26, SpringLayout.SOUTH, txtPrabhkahlongmailcom);
		springLayout.putConstraint(SpringLayout.EAST, txtPrabhkahlongmailcom, -162, SpringLayout.EAST, signupPanel);
		signupPanel.add(txtPrabhkahlongmailcom);
		txtPrabhkahlongmailcom.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		springLayout.putConstraint(SpringLayout.SOUTH, lblEmail, -202, SpringLayout.SOUTH, signupPanel);
		springLayout.putConstraint(SpringLayout.WEST, txtPrabhkahlongmailcom, 75, SpringLayout.EAST, lblEmail);
		springLayout.putConstraint(SpringLayout.WEST, lblEmail, 0, SpringLayout.WEST, lblFirstName);
		signupPanel.add(lblEmail);
		
		JLabel label = new JLabel("*");
		label.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.EAST, lblFirstName);
		springLayout.putConstraint(SpringLayout.EAST, label, -373, SpringLayout.EAST, signupPanel);
		signupPanel.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, label_1, 0, SpringLayout.NORTH, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, label_1, 6, SpringLayout.EAST, lblLastName);
		signupPanel.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, label_2, 0, SpringLayout.NORTH, lblUsername);
		springLayout.putConstraint(SpringLayout.WEST, label_2, 6, SpringLayout.EAST, lblUsername);
		signupPanel.add(label_2);
		
		JLabel label_3 = new JLabel("*");
		springLayout.putConstraint(SpringLayout.NORTH, label_3, 0, SpringLayout.NORTH, txtPrabhkahlongmailcom);
		springLayout.putConstraint(SpringLayout.WEST, label_3, 6, SpringLayout.EAST, lblEmail);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		signupPanel.add(label_3);
		
		JLabel label_4 = new JLabel("*");
		springLayout.putConstraint(SpringLayout.NORTH, label_4, 0, SpringLayout.NORTH, passwordField);
		springLayout.putConstraint(SpringLayout.EAST, label_4, 0, SpringLayout.EAST, label);
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		signupPanel.add(label_4);
		
		lblEnterFirstname = new JLabel("Enter first name");
		lblEnterFirstname.setVisible(false);
		lblEnterFirstname.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEnterFirstname.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.WEST, lblEnterFirstname, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, lblEnterFirstname, -6, SpringLayout.NORTH, textField_1);
		lblEnterFirstname.setVisible(false);
		signupPanel.add(lblEnterFirstname);
		
		lblEnterLastName = new JLabel("Enter last name");
		lblEnterLastName.setVisible(false);
		lblEnterLastName.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterLastName, 6, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, lblEnterLastName, 0, SpringLayout.WEST, textField);
		signupPanel.add(lblEnterLastName);
		
		lblEnterUsername = new JLabel("Enter username");
		lblEnterUsername.setVisible(false);
		lblEnterUsername.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.WEST, lblEnterUsername, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, lblEnterUsername, -6, SpringLayout.NORTH, txtPrabhkahlongmailcom);
		signupPanel.add(lblEnterUsername);
		
		lblEnterEmail = new JLabel("Enter email");
		lblEnterEmail.setVisible(false);
		lblEnterEmail.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterEmail, 6, SpringLayout.SOUTH, txtPrabhkahlongmailcom);
		springLayout.putConstraint(SpringLayout.WEST, lblEnterEmail, 0, SpringLayout.WEST, textField);
		signupPanel.add(lblEnterEmail);
		
		lblEnterPassword = new JLabel("Enter password");
		lblEnterPassword.setVisible(false);
		lblEnterPassword.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, lblEnterPassword, 6, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, lblEnterPassword, 0, SpringLayout.WEST, textField);
		signupPanel.add(lblEnterPassword);
		setupFrame();
	}
	
	private void setupFrame(){
		this.setContentPane(signupPanel);
		//this.pack();
		this.setSize(500, 500);
		FrameCommonProperties.centreWindow(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		if(StringUtils.isBlank(textField.getText())){
			lblEnterFirstname.setVisible(true);
			return;
		} else {
			lblEnterFirstname.setVisible(false);
		}
		
		if(StringUtils.isBlank(textField_1.getText())){
			lblEnterLastName.setVisible(true);
			return;
		} else {
			lblEnterLastName.setVisible(false);
		}
		
		if(StringUtils.isBlank(textField_2.getText())){
			lblEnterUsername.setVisible(true);
			return;
		} else {
			lblEnterUsername.setVisible(false);
		}
		
		if(StringUtils.isBlank(txtPrabhkahlongmailcom.getText())){
			lblEnterEmail.setVisible(true);
			return;
		} else {
			Pattern pattern = Pattern.compile(APIConstant.EMAIL_PATTERN,Pattern.CASE_INSENSITIVE);
			
			Matcher matcher = pattern.matcher(txtPrabhkahlongmailcom.getText());
	        if(!matcher.find()){
	        	lblEnterEmail.setText("Incorrect email");
	        	lblEnterEmail.setVisible(true);
	        	return;
	        } else {
	        	lblEnterEmail.setVisible(false);
	        }
		}
		
		
		if(passwordField.getPassword().length <=0){
			lblEnterPassword.setVisible(true);
			return;
		} else {
			lblEnterPassword.setVisible(false);
		}
		
		
		try {
			HttpPost postRequest = new HttpPost(
				APIConstant.API_BASE_URL + "/user_registration");
			
			JSONObject request = new JSONObject();
			request.put("firstName", textField.getText().trim());
			request.put("lastName",textField_1.getText().trim());
			request.put("username", textField_2.getText().trim());
			request.put("password",passwordField.getPassword());
			request.put("email", txtPrabhkahlongmailcom.getText().trim());
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();

			StringEntity input = new StringEntity(request.toString());
			
			postRequest.addHeader("content-type", "application/json");
			System.out.println(input);
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                        new InputStreamReader((response.getEntity().getContent())));

			StringBuilder sb = new StringBuilder();
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			
			JSONObject httpResponse = new JSONObject(sb.toString());
			if(!(boolean)httpResponse.get("success")){
				System.out.println(httpResponse.get("message"));
				   JOptionPane.showMessageDialog(this,httpResponse.get("message"),
				   "Error",JOptionPane.ERROR_MESSAGE);
			} else {
				 JOptionPane.showMessageDialog(this,httpResponse.get("message"),
						   "Success",JOptionPane.OK_OPTION);
			}

			httpClient.getConnectionManager().shutdown();

		  } catch (HttpHostConnectException httpHostConnectException) {
				JOptionPane.showMessageDialog(this, "Server seems to be down, Try after some time !!!", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
}
