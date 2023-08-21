package main.managercontroller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import main.repository.coffeeRepository;
import main.repository.employeeRepository;
import main.coffee;
import main.coffeestat;

@Controller
public class manage_coffeeController {
	@Autowired
	private coffeeRepository coffeerepository;
	@GetMapping("/manage_coffee")
	public String getmanage_coffee(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		
		List<coffee> coffees = coffeerepository.findallcoffee();
		List<coffeestat> coffeestats = new ArrayList<>();
		for(coffee i : coffees) {
			coffeestat tmp = new coffeestat();
			tmp.setC(i);
			tmp.setId_coffee(i.getId_coffee());
			tmp.setNamecoffee(i.getNamecoffee());
			tmp.setPrice(i.getPrice());
			tmp.setDescribe(i.getDescribe());
			
			coffeestats.add(tmp);
		}
		
		model.addAttribute("fullnamemanager",fullnamemanager);
		model.addAttribute("coffeestats",coffeestats);
		return "manage_coffee";
	}
}
