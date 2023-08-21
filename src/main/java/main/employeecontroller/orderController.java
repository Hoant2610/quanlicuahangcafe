package main.employeecontroller;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main.coffee;
import main.bill;
import main.coffeeorder;
import main.coffee_ordered;
import main.client;
import main.coffeestat;
import main.repository.accountRepository;
import main.repository.billRepository;
import main.repository.clientRepository;
import main.repository.coffeeRepository;
import main.repository.coffee_orderedRepository;
import main.repository.coffeeorderRepository;
@Controller
public class orderController {
	@Autowired
	private billRepository billrepository;
	@Autowired
	private coffeeRepository coffeerepository;
	@Autowired
	private clientRepository clientrepository;
	@Autowired
	private coffeeorderRepository coffeeorderrepository;
	@Autowired
	private coffee_orderedRepository coffee_orderedrepository;
	@GetMapping("/neworder")
	public String get(Model model, HttpServletRequest request,HttpSession session) {
//		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		
		String nameclient = request.getParameter("nameclient");
		String telephone = request.getParameter("telephone");
		model.addAttribute("telephone",telephone);
		model.addAttribute("nameclient",nameclient);
		
		int payment = 0;
		
		String[] quantities = request.getParameterValues("quantity");
		session.setAttribute("quantities", quantities);
		List<coffee> coffees = coffeerepository.findAll();
		List<coffeestat> coffeestats = new ArrayList<>();
		for(int i = 0;i < quantities.length;i++) {
			coffee cf = coffees.get(i) ;
			coffeestat cfstat = new coffeestat();
			cfstat.setC(cf);
			cfstat.setNamecoffee(cf.getNamecoffee());
			cfstat.setQuantity(Integer.valueOf(quantities[i]));
			cfstat.setPrice(cf.getPrice());
			payment += cfstat.getPrice()*cfstat.getQuantity();
			
			coffeestats.add(cfstat);
		}
		session.setAttribute("nameclient", nameclient);
		session.setAttribute("telephone", telephone);
		
		
		
		model.addAttribute("coffeestats",coffeestats);
		model.addAttribute("payment",payment);
		return "neworder";
	}
	
	@PostMapping("/neworder")
	public String post(Model model, HttpServletRequest request,HttpSession session) {
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		String nameclient = (String) session.getAttribute("nameclient") ;
		String telephone = (String) session.getAttribute("telephone");
		if(telephone == "") telephone = "None";
		client c = new client();
		c.setNameclient(nameclient);
		c.setTelephone(telephone);
		clientrepository.save(c);
		
		int id_client = c.getId_client();
		coffeeorder cforder = new coffeeorder();
		cforder.setId_client(id_client);
		coffeeorderrepository.save(cforder);
		
		int id_order = cforder.getId_order();
		bill b = new bill();
		b.setId_order(id_order);
		billrepository.save(b);
		
		List<coffee> coffees = coffeerepository.findAll();
		String[] quantities = (String[]) session.getAttribute("quantities");
		for(int  i = 0;i < quantities.length;i++) {
			int quantiti = Integer.valueOf(quantities[i]);
			if(quantiti != 0) {
				int id_coffee = coffees.get(i).getId_coffee();
				Date date = new Date();
				coffee_ordered co = new coffee_ordered();
				co.setDate(date);
				co.setId_coffee(id_coffee);
				co.setId_order(id_order);
				co.setQuantity(quantiti);
				coffee_orderedrepository.save(co);
			}
		}
		
		model.addAttribute("telephone",telephone);
		model.addAttribute("nameclient",nameclient);
		int payment = 0;
		List<coffeestat> coffeestats = new ArrayList<>();
		for(int i = 0;i < quantities.length;i++) {
			coffee cf = coffees.get(i) ;
			coffeestat cfstat = new coffeestat();
			cfstat.setC(cf);
			cfstat.setNamecoffee(cf.getNamecoffee());
			cfstat.setQuantity(Integer.valueOf(quantities[i]));
			cfstat.setPrice(cf.getPrice());
			payment += cfstat.getPrice()*cfstat.getQuantity();
			
			coffeestats.add(cfstat);
		}
		model.addAttribute("coffeestats",coffeestats);
		model.addAttribute("payment",payment);
		return "reviewbill";
	}
}
