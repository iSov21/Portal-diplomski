package portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
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
	public String addUser(Model model, UserAccount userAccount){
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
	public String editUser(Model model, UserAccount userAccount){
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
