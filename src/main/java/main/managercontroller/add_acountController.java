package main.managercontroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.employee;
import main.account;
import main.repository.accountRepository;
import main.repository.employeeRepository;
@Controller
public class add_acountController {
	@Autowired
	private employeeRepository employeerepository;
	@Autowired
	private accountRepository accountrepository;
	@GetMapping("/addaccount")
	public String add(Model model, HttpServletRequest request,
			@ModelAttribute("error") String error) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		
		model.addAttribute("error",error);
		
		List<employee> employees = employeerepository.findAll();
		model.addAttribute("employees",employees);
		return "add_account";
	}
	@PostMapping("/addaccount")
	public String post(Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		int id_employee = Integer.valueOf(request.getParameter("id_employee"));
		
		account acc = new account();
		acc.setId_employee(id_employee);
		acc.setPassword(password);
		acc.setRole(role);
		acc.setUsername(username);
		
		int check = accountrepository.findusername(username);
		if(check == 0)		accountrepository.save(acc);
		else {
			String errorMessage = "Tên đăng nhập đã tồn tại";
	        redirectAttributes.addFlashAttribute("error", errorMessage);
			return "redirect:/addaccount";
		}
		return "redirect:/manage_account";
	}
}
