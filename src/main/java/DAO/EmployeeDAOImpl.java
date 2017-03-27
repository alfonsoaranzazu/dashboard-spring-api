package DAO;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
			String sql = "insert into EMPLOYEES (NAME, PHONE_NUMBER, SUPERVISORS) values (?, ?, ?)";
			this.template.update(sql, employee.getName(), employee.getPhone(), employee.getSupervisors());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Employee employee) {
		System.out.println(String.format("update employee with id: %s", employee.getId()));
		System.out.println(String.format("update employee with attributes: %s, %s, %s", employee.getName(), employee.getPhone(), employee.getSupervisors()));
		try {
			String sql = String.format("update EMPLOYEES set NAME='%s', PHONE_NUMBER='%s', SUPERVISORS='%s' where EMPLOYEE_ID='%s'", 
					employee.getName(), employee.getPhone(), employee.getSupervisors(), employee.getId());
			this.template.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Employee> getList() {
		return this.template.query("select * from EMPLOYEES", new RowMapper<Employee>() {
			public Employee mapRow(ResultSet rs, int row) {
				Employee employee = new Employee();
				try {
					employee.setId(rs.getInt(1));
					employee.setName(rs.getString(2));
					employee.setPhone(rs.getString(3));
					employee.setSupervisors(rs.getString(4));
					return employee;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		});
	}

	@Override
	public void delete(int id) {
		try {
			String sql = String.format("delete from EMPLOYEES where EMPLOYEE_ID='%s'", id);
			this.template.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}
	
	@Override
	public boolean exists(int employeeId) {
		try {
			Statement statement = this.connection.createStatement();
			String sql = String.format("select EMPLOYEE_ID from EMPLOYEES where EMPLOYEE_ID='%s'", employeeId);
			ResultSet rs = statement.executeQuery(sql);
			return !rs.next() ? false : rs.getInt("EMPLOYEE_ID") > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	@Override
	public boolean exists(Employee employee) {
		try {
			Statement statement = this.connection.createStatement();
			String sql = String.format("select EMPLOYEE_ID from EMPLOYEES where NAME='%s' and PHONE_NUMBER='%s' and SUPERVISORS='%s'", 
					employee.getName(), employee.getPhone(), employee.getSupervisors());	
			ResultSet rs = statement.executeQuery(sql);
			return !rs.next() ? false : rs.getInt("EMPLOYEE_ID") > 0;
		} catch (Exception e) {
			return false;
		} 
	}

}
