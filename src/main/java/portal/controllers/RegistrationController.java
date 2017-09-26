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
	public String registerUserAccount( @ModelAttribute("userDto") @Valid UserDto userDto, 
			BindingResult result, WebRequest request, Errors errors) {
	    
	    UserAccount registered = new UserAccount();
	    if (!result.hasErrors()) {
	        registered = createUserAccount(userDto, result);
	    }
	    if (registered == null) {
	        result.rejectValue("email", "message.regError");
	    }
			
	    return "home";
	}
	
	private UserAccount createUserAccount(UserDto userDto, BindingResult result) {
	    UserAccount registered = null;
	    registered = userAccountService.register(userDto);  
	    return registered;
	}
}
