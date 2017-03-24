package DAO;

import models.Account;

public interface AccountDAO {
	
	public void createAccount(Account account);
	
	public boolean exists(Account account);
}
