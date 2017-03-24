package composite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import DAO.EmployeeDAO;
import models.Employee;
import models.Response;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@RequestMapping(value="/employee", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String createEmployee(@ModelAttribute Employee employee) {
		if (this.employeeDAO.exists(employee)) {
			return new Response(false, "Employee exists").toString();
		} else {
			this.employeeDAO.create(employee);
			return new Response(true, "Employee created").toString();
		}
	}
	
	@RequestMapping(value="/employee", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Employee> getEmployees() {
		return this.employeeDAO.getList();
	}
	
	@RequestMapping(value="/employee", method=RequestMethod.PUT, produces="application/json")
	public @ResponseBody String updateEmployee(@ModelAttribute Employee employee) {
		if (this.employeeDAO.exists(employee)) {
			this.employeeDAO.update(employee);
			return new Response(true, "Employee updated").toString();
		} else {
			return new Response(false, "Employee does not exist").toString();
		}
	}

	@RequestMapping(value="/employee", method=RequestMethod.DELETE, produces="application/json")
	public @ResponseBody String deleteEmployee(@ModelAttribute Employee employee) {
		if (this.employeeDAO.exists(employee)) {
			this.employeeDAO.delete(employee);
			return new Response(true, "Employee deleted").toString();
		} else {
			return new Response(false, "Employee does not exist").toString();
		}
	}
	
	
}
