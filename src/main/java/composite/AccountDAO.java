package composite;

public interface AccountDAO {
	
	public void createAccount(Account account);
	
	public Account get(int accountId);
}
