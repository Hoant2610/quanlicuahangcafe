package main.managercontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import main.repository.employeeRepository;
import main.employee;

@Controller
public class manage_employeeController {
	@Autowired
	private employeeRepository employeerepository;
	@GetMapping("/manage_employee")
	public String getmanage_employee(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		
		List<employee> employees = employeerepository.findallemployee();
		
		model.addAttribute("fullnamemanager",fullnamemanager);
		model.addAttribute("employees",employees);
		return "manage_employee";
	}
}
