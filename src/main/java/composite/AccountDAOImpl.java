package composite;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class AccountDAOImpl implements AccountDAO{
	private JdbcTemplate template;
	private Connection connection;
	
	public AccountDAOImpl(DataSource source, Connection connection) {
		this.template = new JdbcTemplate(source);
		this.connection = connection;
	}
	
	public void createAccount(Account account) {
		try {
			String sql = "INSERT INTO account (EMPLOYEE_ID, USERNAME, PASSWORD) VALUES (?, ?, ?)";
			this.template.update(sql, account.getEmployeeId(), account.getUsername(), account.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Account get(final int accountId) {
		String sql = "SELECT * FROM account WHERE ACCOUNT_ID=" + accountId;
		
		return this.template.query(sql, new ResultSetExtractor<Account>() {
			 
	        @Override
	        public Account extractData(ResultSet rs) throws SQLException, DataAccessException {
	            if (rs.next()) {
	                Account account = new Account(rs.getString("USERNAME"), rs.getString("PASSWORD"));
	                account.setAccountId(accountId);
	                account.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
	                return account;
	            }
	 
	            return null;
	        }
		});
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
