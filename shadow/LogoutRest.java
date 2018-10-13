package shadow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

public final class LogoutRest {
	
	private LogoutRest() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean logout(String authorizationToken,HomeFrame homeFrame){
		
		boolean success = false;
		try {
			HttpPost postRequest = new HttpPost(APIConstant.API_BASE_URL + "/logout");
			postRequest.addHeader("content-type", "application/json");
			postRequest.addHeader("authorization", authorizationToken);
			
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(postRequest);	
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
			success = httpResponse.getBoolean("success");
			
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (HttpHostConnectException httpHostConnectException) {
			
		} catch (IOException e) {
		}
		return success;
	}

}
