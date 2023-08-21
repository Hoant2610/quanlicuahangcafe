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
public class add_employeeController {
	@Autowired
	private employeeRepository employeerepository;
	@GetMapping("/addemployee")
	public String add(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		
		return "add_employee";
	}
	
	@PostMapping("/addemployee")
	public String postadd(Model model, HttpServletRequest request) {
		String fullname=  request.getParameter("fullname");
		String telephone=  request.getParameter("telephone");
		String address=  request.getParameter("address");
		String role=  request.getParameter("role");
		
		employee e = new employee();
		e.setFullname(fullname);
		e.setAddress(address);
		e.setRole(role);
		e.setTelephone(telephone);
		employeerepository.save(e);
		return "redirect:/manage_employee";
	}
}
