package models;

public class Employee {
	private int id;
	private String name;
	private String phone;
	private String supervisors;
	
	public Employee() {
		
	}
	
	public Employee(String name, String phone, String supervisors) {
		this.name = name;
		this.phone = phone;
		this.supervisors = supervisors;
	}
	
	// get methods
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getSupervisors() {
		return this.supervisors;
	}
	
	// set methods
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setSupervisors(String supervisors) {
		this.supervisors = supervisors;
	}
}
