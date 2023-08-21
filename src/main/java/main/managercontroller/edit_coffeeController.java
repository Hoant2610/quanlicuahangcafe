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
import main.repository.employeeRepository;

@Controller
public class edit_coffeeController {
	@Autowired
	private coffeeRepository coffeerepository;
	@GetMapping("/editcoffee/{id}")
	public String edit(Model model, HttpServletRequest request,@PathVariable("id") int id) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		
		coffee e = coffeerepository.findbyid_coffee(id);
		model.addAttribute("coffee",e);
		
		return "edit_coffee";
	}
	@PostMapping("/editcoffee/{id}")
	public String postedit(Model model, HttpServletRequest request,@PathVariable("id") int id) {

		String namecoffee = request.getParameter("namecoffee");
		int price = Integer.valueOf(request.getParameter("price"));
		String describe = request.getParameter("describe");
		
		coffeerepository.updateCoffee(id, namecoffee, price, describe);
		
		return "redirect:/manage_coffee";
	}
}
