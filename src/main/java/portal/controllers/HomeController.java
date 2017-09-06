package portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import portal.repositories.UserAccountRepository;


@Controller
public class HomeController {
	
	@Autowired
	UserAccountRepository sa;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home(){
		return "home";
	}
	
	@RequestMapping(value="/baza", method = RequestMethod.GET)
	public String baza(Model model){
		model.addAttribute("list", sa.findAll());
		return "baza";
	}
	

}
