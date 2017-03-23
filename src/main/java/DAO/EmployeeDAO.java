package DAO;

import java.util.List;

import models.Employee;

public interface EmployeeDAO {
	
	public void create(Employee employee);
	
	public void update(Employee employee);
	
	public List<Employee> getList();
	
	public boolean delete(Employee employee);
	
	public boolean exists(Employee employee);
}
