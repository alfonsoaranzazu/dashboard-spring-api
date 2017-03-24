package DAO;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import models.Account;

public class AccountDAOImpl implements AccountDAO{
	private JdbcTemplate template;
	private Connection connection;
	
	public AccountDAOImpl(DataSource source, Connection connection) {
		this.template = new JdbcTemplate(source);
		this.connection = connection;
	}
	
	public void createAccount(Account account) {
		try {
			String sql = "INSERT INTO account (USERNAME, PASSWORD) VALUES (?, ?)";
			this.template.update(sql, account.getUsername(), account.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean exists(Account account) {
		try {
			Statement statement = this.connection.createStatement();
			String sql = String.format("select ACCOUNT_ID from account where USERNAME='%s' and PASSWORD='%s'", account.getUsername(), account.getPassword());
			ResultSet rs = statement.executeQuery(sql);
			return !rs.next() ? false : rs.getInt("ACCOUNT_ID") > 0;
		} catch (Exception e) {
			return false;
		} 
	}
}
