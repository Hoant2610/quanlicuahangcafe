package main.employeecontroller;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import main.coffee;
import main.repository.accountRepository;
import main.repository.coffeeRepository;

@Controller
public class choosecoffeeController {
	@Autowired
	private coffeeRepository coffeerepository;
	@GetMapping("/choosecoffee")
	public String choose(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		
		List<coffee> coffees = coffeerepository.findAll();
		
		model.addAttribute("coffees",coffees);
		return "choosecoffee";
	}
	@PostMapping("/choosecoffee")
	public String post(Model model, HttpServletRequest request) {
		
		
		
		return "neworder";
	}
}
