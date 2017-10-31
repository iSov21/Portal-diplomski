package portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

}
