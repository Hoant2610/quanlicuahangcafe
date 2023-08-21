package main.managercontroller;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.account;
import main.accountstat;
import main.employee;
import main.repository.accountRepository;
import main.repository.employeeRepository;

@Controller
public class edit_accountController {
	@Autowired
	private accountRepository accountrepository;
	@Autowired
	private employeeRepository employeerepository;
	@GetMapping("/editaccount/{id_account}")
	public String edit(Model model, HttpServletRequest request,
			@PathVariable("id_account") int id_account) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		
		List<employee> employees = employeerepository.findAll();
		model.addAttribute("employees",employees);
		
		account acc = accountrepository.findaccbyid(id_account);
		accountstat account = new accountstat();
		account.setA(acc);
		account.setId_account(acc.getId_account());
		account.setUsername(acc.getUsername());
		account.setPassword(acc.getPassword());
		account.setRole(acc.getRole());
		String fullname = accountrepository.findfullname(acc.getUsername(), acc.getPassword());
		account.setFullname(fullname);
		model.addAttribute("account",account);
		return "edit_account";
	}
	@PostMapping("/editaccount/{id_account}")
	public String post(Model model, HttpServletRequest request,
			@PathVariable("id_account") int id_account) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		int id_employee = Integer.valueOf(request.getParameter("id_employee"));
		
		account acc = new account();
		acc.setId_employee(id_employee);
		acc.setPassword(password);
		acc.setRole(role);
		acc.setUsername(username);
		
		accountrepository.updateAccount(id_account, username, password, role, id_employee);
		
		return "redirect:/manage_account";
	}
}
