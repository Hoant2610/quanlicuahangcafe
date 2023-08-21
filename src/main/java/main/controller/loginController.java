package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import main.repository.accountRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class loginController {
	@Autowired
	private accountRepository accountrepository;
	@GetMapping("/login")
	public String getlogin() {
		return "login";
	}
	
	
	@PostMapping("/login")
	public String postlogin(HttpServletRequest request,Model model,
			RedirectAttributes redirectAttributes) {
		 HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = accountrepository.findfullname(username, password);
		session.setAttribute("fullname", fullname);
		int checklogin = accountrepository.findaccount(username, password);
		String role = accountrepository.findrole(username, password);
		if(checklogin != 0) {
			if(role.equals("manager")) {
				 session.setAttribute("username", username);
				 session.setAttribute("password", password);
				return "redirect:/manager";
			}
			else {
				 session.setAttribute("username", username);
				 session.setAttribute("password", password);
				return "redirect:/employee";
			}
		}
		else model.addAttribute("error","Tên đăng nhập hoặc mật khẩu không đúng");
		return "login";
	}
}
