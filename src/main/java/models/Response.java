package models;

public class Response {
	private boolean success;
	private String message;
	
	public Response() {
		
	}
	
	public Response(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	// get methods
	public boolean success() {
		return this.success;
	}
	
	public String message() {
		return this.message;
	}
	
	// set methods
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return String.format("{\"success\": %s, \"message\": \"%s\"}", this.success, this.message);
	}
	
}
