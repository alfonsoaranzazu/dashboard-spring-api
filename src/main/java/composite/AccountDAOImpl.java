package composite;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class AccountDAOImpl implements AccountDAO{
	private JdbcTemplate template;
	
	public AccountDAOImpl(DataSource source) {
		this.template = new JdbcTemplate(source);
	}
	
	public void createAccount(Account account) {
		if (account.getAccountId() > 0) {
			// update account
			String sql = "UPDATE account SET EMPLOYEE_ID=?, USERNAME=?, PASSWORD=? WHERE ACCOUNT_ID=?";
			this.template.update(sql, account.getEmployeeId(), account.getUsername(), account.getPassword(), account.getAccountId());
		} else {
			// create new account
			String sql = "INSERT INTO account (EMPLOYEE_ID, USERNAME, PASSWORD) VALUES (?, ?, ?)";
			this.template.update(sql, account.getEmployeeId(), account.getUsername(), account.getPassword());
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
}
