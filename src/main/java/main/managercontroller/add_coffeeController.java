package main.managercontroller;
import org.springframework.stereotype.Controller;

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
public class add_coffeeController {
	@Autowired
	private coffeeRepository coffeerepository;
	@GetMapping("/addcoffee")
	public String add(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		
		return "add_coffee";
	}
	
	@PostMapping("/addcoffee")
	public String postadd(Model model, HttpServletRequest request) {
		String namecoffee=  request.getParameter("namecoffee");
		int price=  Integer.valueOf(request.getParameter("price"));
		String describe=  request.getParameter("describe");
		
		coffee c = new coffee();
		c.setDescribe(describe);
		c.setNamecoffee(namecoffee);
		c.setPrice(price);
		coffeerepository.save(c);
		return "redirect:/manage_coffee";
	}
}
