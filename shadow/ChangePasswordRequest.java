package shadow;

import java.io.Serializable;

public class ChangePasswordRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char[] oldPassword;
	private char[] newPassword;
	private char[] confirmPassword;
	
	public char[] getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(char[] oldPassword) {
		this.oldPassword = oldPassword;
	}
	public char[] getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(char[] newPassword) {
		this.newPassword = newPassword;
	}
	public char[] getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(char[] confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	

}
