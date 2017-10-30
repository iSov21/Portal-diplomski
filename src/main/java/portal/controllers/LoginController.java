package portal.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import portal.model.UserAccount;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login(Model model, @ModelAttribute("msg") String msg){
		model.addAttribute("UserAccount", new UserAccount());	
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		model.addAttribute("msg", "Uspješno odlogirani");
		return "logout";
	}

}
