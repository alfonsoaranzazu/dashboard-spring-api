package DAO;

import models.Account;

public interface AccountDAO {
	
	public void createAccount(Account account);
	
	public Account get(int accountId);
	
	public boolean exists(Account account);
}
