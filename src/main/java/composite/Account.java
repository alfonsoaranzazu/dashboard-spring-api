package composite;

import java.util.concurrent.atomic.AtomicLong;

public class Account {
	private int accountId;
	private int employeeId;
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
	
	public int getEmployeeId() {
		return this.employeeId;
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
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
