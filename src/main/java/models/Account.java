package models;

public class Account {
	private int accountId;
	private String username;
	private String password;
	
	public Account() {
		
	}
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	// Getters
	public int getAccountId() {
		return this.accountId;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	// Setters
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
