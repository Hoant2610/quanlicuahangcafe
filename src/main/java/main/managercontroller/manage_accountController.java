package main.managercontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import main.repository.accountRepository;
import main.account;
import main.accountstat;

@Controller
public class manage_accountController {
	@Autowired
	private accountRepository accountrepository;
	@GetMapping("/manage_account")
	public String manage_account(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();

		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
//		String username = (String) session.getAttribute("username");
//		String password = (String) session.getAttribute("password");
		
		List<account> accounts = accountrepository.findallaccount();
		List<accountstat> accountstats = new ArrayList<>();
		
		for(account i : accounts) {
			accountstat tmp = new accountstat();
			tmp.setA(i);
			tmp.setId_account(i.getId_account());
			tmp.setUsername(i.getUsername());
			tmp.setPassword(i.getPassword());
			tmp.setRole(i.getRole());
			tmp.setFullname(accountrepository.findfullname(i.getUsername(), i.getPassword()));
			
			accountstats.add(tmp);
		}
		

		model.addAttribute("accountstats",accountstats);
		model.addAttribute("fullnamemanager", fullnamemanager);
		return "manage_account";
	}
}
