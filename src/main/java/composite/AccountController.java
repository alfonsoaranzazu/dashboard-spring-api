package composite;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class AccountController {
	
	@Autowired
	private AccountDAO accountDAO;

	private AtomicLong employeeId = new AtomicLong();
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public void authenticate(@ModelAttribute Account account) {
		account.setEmployeeId( (int) employeeId.incrementAndGet());
		this.accountDAO.createAccount(account);
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public void signup(@ModelAttribute Account account) {
		account.setEmployeeId( (int) employeeId.incrementAndGet());
		this.accountDAO.createAccount(account);
	}
}
