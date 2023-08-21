package main.managercontroller;
import org.springframework.stereotype.Controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.employee;
import main.repository.employeeRepository;
@Controller
public class delete_employeeController {
	@Autowired
	private employeeRepository employeerepository;
	@GetMapping("/deleteemployee/{id}")
	public String edit(Model model, HttpServletRequest request,@PathVariable("id") int id) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		
		employee e = employeerepository.findbyid_employee(id);
		model.addAttribute("employee",e);
		
		return "delete_employee";
	}
	@PostMapping("/deleteemployee/{id}")
	public String postedit(Model model, HttpServletRequest request,@PathVariable("id") int id) {
		employeerepository.deleteaccountById(id);
		employeerepository.deleteEmployeeById(id);
		
		return "redirect:/manage_employee";
	}
}
