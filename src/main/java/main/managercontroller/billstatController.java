package main.managercontroller;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import main.bill;
import main.coffee;
import main.billstat;
import main.coffeestat;
import main.repository.billRepository;
import main.repository.coffeeRepository;
@Controller
public class billstatController {
	@Autowired
	private billRepository billrepository;
	@Autowired
	private coffeeRepository coffeerepository;
	@GetMapping("/bill/{id_coffee}")
	public String billstat(Model model, HttpServletRequest request,
			@PathVariable("id_coffee") int id_coffee) {
		HttpSession session = request.getSession();
		String fullnamemanager = (String) session.getAttribute("fullnamemanager");
		session.setAttribute(fullnamemanager, fullnamemanager);
		model.addAttribute("fullnamemanager",fullnamemanager);
		
		String sd = (String) session.getAttribute("sd");
		String ed = (String) session.getAttribute("ed");
		

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date startDate = format.parse(sd);
            Date endDate = format.parse(ed);

            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            
            List<bill> bills = billrepository.findByDate(id_coffee,startDate, endDate);
            List<billstat> billstats = new ArrayList<>();
            for(bill i : bills) {
            	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            	
            	billstat tmp = new billstat();
            	int id_client = billrepository.findIdKH(id_coffee, i.getId_bill(), startDate, endDate);;
            	String telephone = billrepository.findTelephoneclient(id_coffee, i.getId_bill(), startDate, endDate);
            	String nameclient = billrepository.findNameclient(id_coffee, i.getId_bill(), startDate, endDate);;
            	Date datebuy = billrepository.findDateById(id_coffee,i.getId_bill(),startDate, endDate);
            	int totalturn = billrepository.quantity(id_coffee, id_client, startDate, endDate);
            	int totalrevenue = billrepository.totalrevenue(id_coffee, id_client, startDate, endDate);
            	tmp.setB(i);
            	tmp.setId_bill(id_client);
            	tmp.setDate(dateFormat.format(datebuy));
            	tmp.setNameclient(nameclient);
            	tmp.setTotalrevenue(totalrevenue);
            	tmp.setTotalturn(totalturn);
            	tmp.setTelephone(telephone);
            	billstats.add(tmp);
            }
            
            String sd1 = format1.format(startDate);
            String ed1 = format1.format(endDate);
            model.addAttribute("sd",sd1);
            model.addAttribute("ed",ed1);
            Collections.sort(billstats, new Comparator<billstat>() {
                @Override
                public int compare(billstat o1, billstat o2) {
                    if(o1.getTotalrevenue() >= o2.getTotalrevenue()) return -1;
                    return 1;
                }
            });
            Collections.sort(billstats, new Comparator<billstat>() {
                @Override
                public int compare(billstat o1, billstat o2) {
                    String d1 = o1.getDate();
                    String d2 = o2.getDate();
                    try {
						Date dateString1 = format1.parse(d1);
						Date dateString2 = format1.parse(d2);
						return dateString1.compareTo(dateString2);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return 0;
                }
            });
            coffee i = new coffee();
            i = coffeerepository.findbyid_coffee(id_coffee);
            String namecoffee = i.getNamecoffee();
            model.addAttribute("namecoffee",namecoffee);
            model.addAttribute("billstats",billstats);
            return "billstat";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
		return null;
		
	}
}
