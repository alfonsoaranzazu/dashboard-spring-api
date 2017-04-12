package composite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import DAO.EmployeeDAO;
import models.Employee;
import models.Response;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/employee", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String createEmployee(@RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("supervisors") String supervisors) {
		Employee employee = new Employee(name, phone, supervisors);
		if (this.employeeDAO.exists(employee)) {
			return new Response(false, "Employee exists").toString();
		} else {
			this.employeeDAO.create(employee);
			return new Response(true, "Employee created").toString();
		}
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/employee", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Employee> getEmployees() {
		return this.employeeDAO.getList();
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/employee", method=RequestMethod.PUT, produces="application/json")
	public @ResponseBody String updateEmployee(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("phone") String phone, @RequestParam("supervisors") String supervisors) {
		System.out.println("update employee controller");
		System.out.println(String.format("employee ID: %s", id));
		if (this.employeeDAO.exists(id)) {
			Employee employee = new Employee(name, phone, supervisors);
			employee.setId(id);
			this.employeeDAO.update(employee);
			return new Response(true, "Employee updated").toString();
		} else {
			return new Response(false, "Employee does not exist").toString();
		}
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/employee", method=RequestMethod.DELETE, produces="application/json")
	public @ResponseBody String deleteEmployee(@RequestParam("id") int id) {
		if (this.employeeDAO.exists(id)) {
			this.employeeDAO.delete(id);
			return new Response(true, "Employee deleted").toString();
		} else {
			return new Response(false, "Employee does not exist").toString();
		}
	}
	
	
}
