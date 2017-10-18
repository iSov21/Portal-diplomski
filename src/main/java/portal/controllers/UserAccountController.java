package portal.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import portal.model.EmployerDetails;
import portal.model.Role;
import portal.model.StudentDetails;
import portal.model.UserAccount;
import portal.repositories.RoleRepository;
import portal.services.EmployerDetailsService;
import portal.services.StudentDetailsService;
import portal.services.UserAccountService;

@Controller
@RequestMapping(value="/user")
public class UserAccountController {
	
	@Autowired
	UserAccountService userAccountService;
	
	@Autowired
	StudentDetailsService studentDetailsService;
	
	@Autowired
	EmployerDetailsService employerDetailsService;
	
	@Autowired
	RoleRepository roleRepository;
	
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
	
	@RequestMapping(value="/studentDetails", method = RequestMethod.GET)
	public String studentDetails(Model model, Authentication authentication, @ModelAttribute("msg") String msg){
		UserAccount user = userAccountService.findByUsername(authentication.getName());
		StudentDetails details = studentDetailsService.findById(user.getId());
		if( details == null ) {
			StudentDetails newDetails = new StudentDetails();
			newDetails.setUserId(user.getId());
			model.addAttribute("StudentDetails", newDetails);
		}
		else {
			model.addAttribute("StudentDetails", details);
		}
		model.addAttribute("msg", msg);
		return "studentDetails";
	}
	
	@RequestMapping(value="/studentDetails", method = RequestMethod.POST)
	public String saveDetails(Model model, @ModelAttribute("StudentDetails") StudentDetails studentDetails, 
			@RequestParam("cv2") MultipartFile file) throws Exception{
		
		if(file.getSize()==0) {
			studentDetails.setCv(studentDetailsService.findById(studentDetails.getUserId()).getCv());
		}
		else {
			studentDetails.setCv(file.getBytes());
		}
		studentDetailsService.saveDetails(studentDetails);
		model.addAttribute("StudentDetails", studentDetails);
		model.addAttribute("msg", "Detalji spremljeni");
	
		return "studentDetails";
	}
	
	@RequestMapping(value="/employerDetails", method = RequestMethod.GET)
	public String employerDetails(Model model, Authentication authentication, @ModelAttribute("msg") String msg){
		
		UserAccount user = userAccountService.findByUsername(authentication.getName());
		EmployerDetails details = employerDetailsService.findById(user.getId());
		if( details == null ) {
			EmployerDetails newDetails = new EmployerDetails();
			newDetails.setUserId(user.getId());
			model.addAttribute("EmployerDetails", newDetails);
		}
		else {
			model.addAttribute("EmployerDetails", details);
		}
		return "employerDetails";
	}
	
	@RequestMapping(value="/employerDetails", method = RequestMethod.POST)
	public String saveEmployerDetails(Model model, @ModelAttribute("EmployerDetails") EmployerDetails employerDetails){
		
		employerDetailsService.saveDetails(employerDetails);
		model.addAttribute("EmployerDetails", employerDetails);
		model.addAttribute("msg", "Detalji spremljeni");
	
		return "employerDetails";
	}
	
	@RequestMapping(value="/showEmployerDetails", method = RequestMethod.GET)
	public String showEmployerDetails(Model model, @RequestParam("username") String username){
		UserAccount userAccount = userAccountService.findByUsername(username);
		EmployerDetails details = employerDetailsService.findById(userAccount.getId());
		
		model.addAttribute("employerDetails", details);
		model.addAttribute("username", username);
		return "showEmployerDetails";
	}
	
	@RequestMapping(value="/addRole", method = RequestMethod.GET)
	public String addRole(@RequestParam("userId") Long userId, Model model){
		model.addAttribute("Role", new Role());
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("userId", userId);
		return "roleAdd";
	}
	
	@RequestMapping(value="/addRole", method = RequestMethod.POST)
	public String saveRole(@RequestParam("userId") Long userId, Model model, @ModelAttribute("Role") Role role){
		UserAccount userAccount = userAccountService.findById(userId);
		userAccount.setRole(roleRepository.findById(role.getId()));
		userAccountService.saveUserRole(userAccount);
		return "redirect:/user/list";
	}
}
