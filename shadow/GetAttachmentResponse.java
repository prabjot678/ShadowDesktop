package shadow;

import java.util.List;


public class GetAttachmentResponse extends CommonResponse {
	
	List<AttachmentResponse> attachments;

	public List<AttachmentResponse> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentResponse> attachments) {
		this.attachments = attachments;
	}
	
	
	
}
