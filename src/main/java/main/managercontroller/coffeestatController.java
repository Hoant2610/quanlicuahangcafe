package main.managercontroller;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
public class coffeestatController {
	@Autowired
	private coffeeRepository coffeerepository;
	@GetMapping("/coffeestat")
	public String coffeestat(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		
		String sd = request.getParameter("sd");
		String ed = request.getParameter("ed");
		session.setAttribute("sd", sd);
		session.setAttribute("ed", ed);
		List<coffee> coffees = coffeerepository.findAll();		
		List<coffeestat> coffeestats = new ArrayList<>();
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date sdtmp = dateFormat.parse(sd);
			Date edtmp = dateFormat.parse(ed);
			dateFormat.applyPattern("dd/MM/yyyy");
			ed = dateFormat.format(edtmp);
			sd = dateFormat.format(sdtmp);
			model.addAttribute("ed", ed);
			model.addAttribute("sd", sd);
			List<coffee> allcoffees = coffeerepository.findAll();
			List<coffee> allcoffeebydate = coffeerepository.findByDate(sdtmp,edtmp);
			for(coffee i : allcoffees) {
				coffeestat tmp = new coffeestat();
				if(allcoffeebydate.contains(i)) {
					
					tmp.setC(i);
					int quantity = coffeerepository.quantity(i.getId_coffee(), sdtmp, edtmp);
					int totalturn = coffeerepository.totalturn(i.getId_coffee(), sdtmp, edtmp);
					long totalrevenue = coffeerepository.totalRevenue(i.getId_coffee(), sdtmp, edtmp);
//					int quantity = 12;
//					int totalturn = 12;
//					long totalrevenue = 10;
					tmp.setTotalrevenue(totalrevenue);				
					tmp.setTotalturn(totalturn);
					tmp.setQuantity(quantity);
					coffeestats.add(tmp);
				}
				else {
					tmp.setC(i);
					tmp.setTotalrevenue(0);				
					tmp.setTotalturn(0);
					tmp.setQuantity(0);
					coffeestats.add(tmp);
				}
				

			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Collections.sort(coffeestats, new Comparator<coffeestat>() {
            @Override
            public int compare(coffeestat o1, coffeestat o2) {
                if(o1.getTotalrevenue() >= o2.getTotalrevenue()) return -1;
                return 1;
            }
        });
        
	    model.addAttribute("coffeestats",coffeestats);
	    if(coffeestats.size()!=0) return "coffeestat";
	    return "coffeestat404";
	}
}
