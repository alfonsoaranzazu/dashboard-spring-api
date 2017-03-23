package composite;
import models.Account;
import models.Response;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DAO.AccountDAO;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class AccountController {
	
	@Autowired
	private AccountDAO accountDAO;

	private AtomicLong employeeId = new AtomicLong();
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String authenticate(@ModelAttribute Account account) {
		if (this.accountDAO.exists(account)) {
			return new Response(true, "Valid login credentials").toString();
		} else {
			return new Response(false, "Invalid login credentials").toString(); 
		}
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String signup(@ModelAttribute Account account) {
		if (this.accountDAO.exists(account)) {
			return new Response(false, "Account exists").toString();
		} else {
			account.setEmployeeId( (int) employeeId.incrementAndGet());
			this.accountDAO.createAccount(account);
			return new Response(true, "Account created").toString();
		}
	}
}
