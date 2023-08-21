package main.managercontroller;
import org.springframework.stereotype.Controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.coffee;
import main.coffeestat;
import main.repository.coffeeRepository;
@Controller
public class filldateController {
	@Autowired
	private coffeeRepository coffeerepository;
	@GetMapping("/stat")
	public String statget(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		
		List<coffee> coffees = coffeerepository.findAll();		
		List<coffeestat> coffeestats = new ArrayList<>();
		
//		for(coffee i : coffees) {
//			int totalturn = 0;
//			int totalrevenue = 0;
//			
//			coffeestat tmp = new coffeestat();
//			tmp.setC(i);
//			tmp.setTotalrevenue(totalrevenue);
//			tmp.setTotalturn(totalturn);
//		}
		return "filldate";
	}
}
