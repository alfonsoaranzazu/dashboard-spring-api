package DAO;

import java.util.List;

import models.Employee;

public interface EmployeeDAO {
	
	public void create(Employee employee);
	
	public void update(Employee employee);
	
	public List<Employee> getList();
	
	public void delete(int id);
	
	public boolean exists(int employeeId);
	
	public boolean exists(Employee employee);
}
