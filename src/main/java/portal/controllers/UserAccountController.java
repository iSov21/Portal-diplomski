package portal.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import portal.model.UserAccount;
import portal.services.UserAccountService;

@Controller
@RequestMapping(value="/user")
public class UserAccountController {
	
	@Autowired
	UserAccountService userAccountService;
	
	@RequestMapping(value= {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", userAccountService.findAllUsers());
		return "userList";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("UserAccount", new UserAccount());
		return "userAdd";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addUser(Model model, @Valid @ModelAttribute("UserAccount") UserAccount userAccount, BindingResult result){
		
		if(result.hasErrors()){	
			
		/*	if(userAccount.getUsername().equals(""))
				model.addAttribute("errorMsg", "Korisničko ime ne može biti prazno!");
			
			else if(userAccountService.findByUsername(userAccount.getUsername())!=null)
				model.addAttribute("errorMsg", "Korisničko ime se već koristi!");
			
			else if(userAccount.getPassword().equals(""))
				model.addAttribute("errorMsg", "Lozinka ne može biti prazna!");
			
			else if(userAccount.getEmail().equals(""))
				model.addAttribute("errorMsg", "Email ne može biti prazan!");*/
			
			return "userAdd";
		}
		userAccountService.saveUser(userAccount);
		model.addAttribute("list", userAccountService.findAllUsers());
		return "userList";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") Long id, Model model, UserAccount userAccount){
		model.addAttribute("UserAccount", userAccountService.findById(id) );
		return "userEdit";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String editUser(Model model,@Valid @ModelAttribute("UserAccount") UserAccount userAccount, BindingResult result){
		if(result.hasErrors()){	
			return "userEdit";
		}
		
		userAccountService.updateUser(userAccount);
		model.addAttribute("list", userAccountService.findAllUsers());
		return "userList";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String deleteUser(@RequestParam("id") Long id,  Model model){
		userAccountService.deleteUser(id);
		model.addAttribute("list", userAccountService.findAllUsers());
		return "userList";
	}
}
