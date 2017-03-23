package DAO;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import models.Employee;

public class EmployeeDAOImpl implements EmployeeDAO{
	private JdbcTemplate template;
	private Connection connection;
	
	public EmployeeDAOImpl(DataSource source, Connection connection) {
		this.template = new JdbcTemplate(source);
		this.connection = connection;
	}

	@Override
	public void create(Employee employee) {
		try {
			String sql = "INSERT INTO employees (NAME, PHONE_NUMBER, SUPERVISORS) VALUES (?, ?, ?)";
			this.template.update(sql, employee.getName(), employee.getPhone(), employee.getSupervisors());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Employee employee) {
		
		
	}

	@Override
	public List<Employee> getList() {
		
		return null;
	}

	@Override
	public boolean delete(Employee employee) {
		try {
			Statement statement = this.connection.createStatement();
			String sql = String.format("delete from employees where EMPLOYEE_ID='%s'", employee.getId());
			ResultSet rs = statement.executeQuery(sql);
			return true;
		} catch (Exception e) {
			return false;
		} 
		
	}
	
	@Override
	public boolean exists(Employee employee) {
		try {
			Statement statement = this.connection.createStatement();
			String sql = String.format("select EMPLOYEE_ID from employees where NAME='%s'", employee.getName());
			ResultSet rs = statement.executeQuery(sql);
			return !rs.next() ? false : rs.getInt("EMPLOYEE_ID") > 0;
		} catch (Exception e) {
			return false;
		} 
	}

}
