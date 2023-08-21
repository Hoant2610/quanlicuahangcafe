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

import main.coffee;
import main.repository.coffeeRepository;

@Controller
public class delete_coffeeController {
	@Autowired
	private coffeeRepository coffeerepository;
	@GetMapping("/deletecoffee/{id}")
	public String edit(Model model, HttpServletRequest request,@PathVariable("id") int id) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		
		coffee c = new coffee();
		c = coffeerepository.findbyid_coffee(id);
		
		model.addAttribute("coffee",c);
		return "delete_coffee";
	}
	@PostMapping("/deletecoffee/{id}")
	public String postedit(Model model, HttpServletRequest request,@PathVariable("id") int id) {

		coffeerepository.deleteCoffeeById(id);
		
		return "redirect:/manage_coffee";
	}
}
