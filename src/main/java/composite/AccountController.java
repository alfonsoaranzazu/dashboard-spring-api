package composite;
import models.Account;
import models.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DAO.AccountDAO;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class AccountController {
	
	@Autowired
	private AccountDAO accountDAO;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/authenticate", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String authenticate(@RequestParam("username") String username, @RequestParam("password") String password) {
		Account account = new Account(username, password);
		if (this.accountDAO.exists(account)) {
			return new Response(true, "Valid login credentials").toString();
		} else {
			return new Response(false, "Invalid login credentials").toString(); 
		}
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/account", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String signup(@RequestParam("username") String username, @RequestParam("password") String password) {
		Account account = new Account(username, password);
		if (this.accountDAO.exists(account)) {
			return new Response(false, "Account exists").toString();
		} else {
			this.accountDAO.createAccount(account);
			return new Response(true, "Account created").toString();
		}
	}
}
