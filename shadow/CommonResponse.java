package shadow;

import java.util.Date;

public class CommonResponse {

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getResponseCreatedAt() {
		return responseCreatedAt;
	}

	public void setResponseCreatedAt(Long responseCreatedAt) {
		this.responseCreatedAt = responseCreatedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private boolean success;
	private String message;
	private Long responseCreatedAt;
	

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	private String id;
	private String createdAt;
	private String lastModified;

}
