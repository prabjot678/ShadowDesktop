package shadow;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class ProjectRest {

	public static List<Project> getProjects(String authorizationToken, HomeFrame homeFrame) {

		try {
			HttpGet getRequest = new HttpGet(APIConstant.API_BASE_URL + "/project");
			JSONObject request = new JSONObject();
			request.put("authorization", authorizationToken);
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			getRequest.addHeader("content-type", "application/json");
			getRequest.addHeader("authorization", authorizationToken);
			HttpResponse response = httpClient.execute(getRequest);

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

			JSONArray jsonArray = (JSONArray) httpResponse.get("projects");

			List<Project> projects = new ArrayList<>(jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Project project = new Project();
				if (!jsonObject.isNull("description")) {
					project.setDescription(jsonObject.getString("description"));
				}
				project.setId(jsonObject.getString("id"));
				project.setName(jsonObject.getString("name"));
				projects.add(project);
			}
			httpClient.getConnectionManager().shutdown();
			return projects;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (HttpHostConnectException httpHostConnectException) {
			JOptionPane.showMessageDialog(homeFrame, "Check your internet connection !!!", "Error",
					JOptionPane.ERROR_MESSAGE);

		} catch (IOException e) {

			JOptionPane.showMessageDialog(homeFrame, "Server seems to be down, Try after some time !!!", "Error",
					JOptionPane.ERROR_MESSAGE);

		}
		return null;
	}

	public static Project saveProjectName(String name, String authorizationToken) {
		try {
			HttpPost postRequest = new HttpPost(APIConstant.API_BASE_URL + "/project");
			postRequest.addHeader("content-type", "application/json");
			postRequest.addHeader("authorization", authorizationToken);

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			JSONObject request = new JSONObject();
			request.put("name", name);
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
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			System.out.println("Output from Server .... \n" + sb.toString());
			JSONObject httpResponse = new JSONObject(sb.toString());
			Project project = new Project();
			project.setId(httpResponse.getString("id"));
			project.setName(httpResponse.getString("name"));
			httpClient.getConnectionManager().shutdown();
			return project;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (HttpHostConnectException httpHostConnectException) {

		} catch (IOException e) {
			e.printStackTrace();

		}
		return null;

	}

	public static Project updateProject(Project project, String authorizationToken, HomeFrame homeFrame) {

		try {
			HttpPut putRequest = new HttpPut(APIConstant.API_BASE_URL + "/project");
			putRequest.addHeader("content-type", "application/json");
			putRequest.addHeader("authorization", authorizationToken);
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			JSONObject request = new JSONObject();
			request.put("name", project.getName());
			request.put("id", project.getId());
			request.put("description", project.getDescription());
			StringEntity input = new StringEntity(request.toString());
			putRequest.setEntity(input);

			HttpResponse response = httpClient.execute(putRequest);

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
			httpClient.getConnectionManager().shutdown();
			JSONObject httpResponse = new JSONObject(sb.toString());
			project.setDescription(httpResponse.getString("description"));
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (HttpHostConnectException httpHostConnectException) {

			JOptionPane.showMessageDialog(homeFrame, "Check your internet connection !!!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(homeFrame, "Server seems to be down, Try after some time !!!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return project;
	}

	public static boolean deleteProject(String authorizationToken, String projectId, HomeFrame homeFrame) {

		boolean success = false;
		try {
			HttpDelete deleteRequest = new HttpDelete(APIConstant.API_BASE_URL + "/project");
			deleteRequest.addHeader("content-type", "application/json");
			deleteRequest.addHeader("authorization", authorizationToken);
			deleteRequest.addHeader("id", projectId);

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(deleteRequest);

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
			success = httpResponse.getBoolean("success");

		} catch (MalformedURLException e) {

		} catch (HttpHostConnectException httpHostConnectException) {

			JOptionPane.showMessageDialog(homeFrame, "Check your internet connection !!!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(homeFrame, "Server seems to be down, Try after some time !!!", "Error",
					JOptionPane.ERROR_MESSAGE);

		}
		return success;
	}
	
	public static boolean updateProjectName(String authorizationToken,String projectId,String projectName,HomeFrame homeFrame) {

		boolean success = false;
		try {
			HttpPut putRequest = new HttpPut(APIConstant.API_BASE_URL + "/project_name");
			putRequest.addHeader("content-type", "application/json");
			putRequest.addHeader("authorization", authorizationToken);
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			JSONObject request = new JSONObject();
			request.put("name", projectName);
			request.put("id", projectId);
			StringEntity input = new StringEntity(request.toString());
			putRequest.setEntity(input);

			HttpResponse response = httpClient.execute(putRequest);

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
			httpClient.getConnectionManager().shutdown();
			JSONObject httpResponse = new JSONObject(sb.toString());
			success = httpResponse.getBoolean("success");
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (HttpHostConnectException httpHostConnectException) {

			JOptionPane.showMessageDialog(homeFrame, "Check your internet connection !!!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(homeFrame, "Server seems to be down, Try after some time !!!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return success;
	}
	
	public static GetAttachmentResponse getAttachments(String authorizationToken,String projectId,JFrame homeFrame) {

		GetAttachmentResponse attachmentServerResponse = null;
		try {
			
			String url = APIConstant.API_BASE_URL + "/project/" + projectId + "/" + "document";
			
			HttpGet getRequest = new HttpGet(url);
			getRequest.addHeader("content-type", "application/json");
			getRequest.addHeader("authorization", authorizationToken);
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
			HttpResponse response = httpClient.execute(getRequest);

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
			httpClient.getConnectionManager().shutdown();

			JSONArray jsonArray = (JSONArray) httpResponse.get("attachments");
			
			List<AttachmentResponse> attachments = new ArrayList<>();
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				AttachmentResponse attachmentResponse = new AttachmentResponse();
				
				attachmentResponse.setId(jsonObject.getString("id"));
				attachmentResponse.setName(jsonObject.getString("name"));
				attachmentResponse.setURL(jsonObject.getString("url"));
				attachmentResponse.setSize(jsonObject.getString("size"));
				attachmentResponse.setCreatedAt(jsonObject.getString("attachmentUploadedDate"));
				attachmentResponse.setLastModified(jsonObject.getString("attachmentLastModifiedDate"));
				attachments.add(attachmentResponse);
			}
			
			attachmentServerResponse = new GetAttachmentResponse();
			attachmentServerResponse.setSuccess(httpResponse.getBoolean("success"));
			attachmentServerResponse.setAttachments(attachments);
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (HttpHostConnectException httpHostConnectException) {

			JOptionPane.showMessageDialog(homeFrame, "Check your internet connection !!!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(homeFrame, "Server seems to be down, Try after some time !!!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return attachmentServerResponse;
	}
	
	public static boolean uploadDocument(String authToken,String filePath,String projectId,JFrame jFrame){
		
		boolean success = false;
		try {
			String url = APIConstant.API_BASE_URL + "/project/" + projectId + "/" + "document";
			HttpPost post = new HttpPost(url);
			post.addHeader("authorization", authToken);
			CloseableHttpClient client = HttpClientBuilder.create().build();

			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			multipartEntityBuilder.addPart("file", new FileBody(new File(filePath)));
			HttpEntity httpEntity = multipartEntityBuilder.build();
			post.setEntity(httpEntity);

			HttpResponse response = client.execute(post);
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
			client.getConnectionManager().shutdown();
			JSONObject httpResponse = new JSONObject(sb.toString());
			success = httpResponse.getBoolean("success");
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (HttpHostConnectException httpHostConnectException) {

			JOptionPane.showMessageDialog(jFrame, "Check your internet connection !!!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(jFrame, "Server seems to be down, Try after some time !!!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return success;
		
	}

}
