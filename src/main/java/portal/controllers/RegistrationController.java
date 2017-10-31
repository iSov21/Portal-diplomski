package portal.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portal.model.UserAccount;
import portal.model.UserDto;
import portal.services.UserAccountService;

@Controller
public class RegistrationController {

	@Autowired
	UserAccountService userAccountService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
	    model.addAttribute("userDto", new UserDto());
	    return "registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUserAccount(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, 
			Model model, Errors error,  RedirectAttributes redirectAttrs) {
	    
		if(!userDto.getPassword().equals(userDto.getMatchingPassword())){
			result.reject("matchingPassword");
			error.rejectValue("matchingPassword", "match", "Ponovoljena lozinka ne odgovara upisanoj");
		}
		
		if(result.hasErrors()){	
			return "registration";
		}
		
	    UserAccount registered = new UserAccount();
	        
	    if (!result.hasErrors() ) {
	        registered = userAccountService.register(userDto);
	    }
	   
	    if (registered == null) {
			error.rejectValue("username", "exist", "Korisničko ime već postoji");
	    	return "registration";
	    }
		
	    redirectAttrs.addAttribute("msg", "Uspješno ste registrirani na portal!" );
	    return "redirect:/login";
	}
}
