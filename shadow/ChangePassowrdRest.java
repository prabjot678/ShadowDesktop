package shadow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public class ChangePassowrdRest {

	public static CommonResponse changePassword(String authorizationToken, ChangePasswordRequest changePasswordRequest,
			ForgotPasswordFrame forgotPasswordFrame) {
		try {

			HttpPost postRequest = new HttpPost(APIConstant.API_BASE_URL + "/change_password");
			postRequest.addHeader("content-type", "application/json");
			postRequest.addHeader("authorization", authorizationToken);
			
			JSONObject request = new JSONObject();
			request.put("oldPassword", changePasswordRequest.getOldPassword());
			request.put("newPassword", changePasswordRequest.getNewPassword());
			request.put("confirmPassword", changePasswordRequest.getConfirmPassword());

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			StringEntity input = new StringEntity(request.toString());
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			System.out.println(response.getStatusLine().getStatusCode());

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			StringBuilder sb = new StringBuilder();
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			JSONObject httpResponse = new JSONObject(sb.toString());
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setSuccess(httpResponse.getBoolean("success"));
			commonResponse.setMessage(httpResponse.getString("message"));
			return commonResponse;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (HttpHostConnectException httpHostConnectException) {
			JOptionPane.showMessageDialog(forgotPasswordFrame, "Check your internet connection !!!", "Error",
					JOptionPane.ERROR_MESSAGE);

		} catch (IOException e) {

			JOptionPane.showMessageDialog(forgotPasswordFrame, "Server seems to be down, Try after some time !!!",
					"Error", JOptionPane.ERROR_MESSAGE);

		}
		return null;
	}
	
	public static CommonResponse forgotPassword(String email,
			JFrame loginFrame) {
		try {

			HttpPost postRequest = new HttpPost(APIConstant.API_BASE_URL + "/forgot_password");
			postRequest.addHeader("content-type", "application/json");
			
			JSONObject request = new JSONObject();
			request.put("email", email);

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			StringEntity input = new StringEntity(request.toString());
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			System.out.println(response.getStatusLine().getStatusCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			
			
			StringBuilder sb = new StringBuilder();
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}	
			JSONObject httpResponse = new JSONObject(sb.toString());
			CommonResponse commonResponse = new CommonResponse();
			commonResponse.setSuccess(httpResponse.getBoolean("success"));
			commonResponse.setMessage(httpResponse.getString("message"));
			
			
			if (response.getStatusLine().getStatusCode() != 200) {
				JOptionPane.showMessageDialog(loginFrame, commonResponse.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			return commonResponse;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (HttpHostConnectException httpHostConnectException) {
			JOptionPane.showMessageDialog(loginFrame, "Check your internet connection !!!", "Error",
					JOptionPane.ERROR_MESSAGE);

		} catch (IOException e) {

			JOptionPane.showMessageDialog(loginFrame, "Server seems to be down, Try after some time !!!",
					"Error", JOptionPane.ERROR_MESSAGE);

		}
		return null;
	}

}
