package main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import main.repository.accountRepository;

@Controller
public class managerController {
	@Autowired
	private accountRepository accountrepository;

	@GetMapping("/manager")
	public String manager(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		String fullnamemanager = accountrepository.findfullname(username, password);
		String id_manager = accountrepository.findid(username, password);
		String address = accountrepository.findaddress(username, password);
		String telephone = accountrepository.findtelephone(username, password);

		model.addAttribute("fullnamemanager", fullnamemanager);
		model.addAttribute("id_manager", id_manager);
		model.addAttribute("address", address);
		model.addAttribute("telephone", telephone);

		session.setAttribute("fullnamemanager", fullnamemanager);
		session.setAttribute("username", username);
		session.setAttribute("password", password);

//		if(username != null) {
//			session.setAttribute("username",username);
//			session.setAttribute("password",password);
//			return "redirect:/manage_account";
//		}
		return "manager";
	}
}
