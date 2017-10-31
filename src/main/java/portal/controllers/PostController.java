package portal.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import portal.model.Category;
import portal.model.Post;
import portal.model.StudentDetails;
import portal.model.UserAccount;
import portal.services.CategoryService;
import portal.services.EmployerDetailsService;
import portal.services.PostService;
import portal.services.StudentDetailsService;
import portal.services.UserAccountService;

@Controller
@RequestMapping("/post")
@SessionScope
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UserAccountService userAccountService;
	
	@Autowired
	StudentDetailsService studentDetailsService;
	
	@Autowired
	EmployerDetailsService employerDetailsService;
	
	
	@RequestMapping(value="/listAdmin", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(required = false) Integer page){
		List<Post> posts = postService.findAllPosts();		
		model = makePaginatedList(posts, model, page, 6);
		return "postListAdmin";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String add(Model model, Authentication authentication, RedirectAttributes redirectAttrs){
		
		UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
		
		if(!employerDetailsService.findByUserId(userAccount.getId()) && !userAccount.getRole().getName().equals("ADMIN")){
			redirectAttrs.addAttribute("msg", "Molim ispunite detalje prije objavljivanja posta!" );
			return "redirect:/user/employerDetails";
		}
		
		model.addAttribute("Post", new Post());
		model.addAttribute("category", categoryService.findAllCategories());
		return "postAdd";
	}
	
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addPost(Model model, @Valid @ModelAttribute("Post") Post post, BindingResult result, 
			@RequestParam("logo2") MultipartFile logo, HttpServletRequest request) throws IOException{
		
		if(result.hasErrors()){	
			model.addAttribute("category", categoryService.findAllCategories());
			return "postAdd";
		}
		
	    post.setLogo(fileToString(logo));   
	   
		postService.savePost(post);
		model.addAttribute("list", postService.findAllPosts());
		
		if(request.isUserInRole("ADMIN")) {
			return "postListAdmin";
		}
		return "redirect:/post/list";
	}
	
	public String fileToString(MultipartFile file) throws IOException{
		byte[] encodeBase64 = Base64.getEncoder().encode(file.getBytes());
	    String base64Encoded = new String(encodeBase64, "UTF-8");
	    return base64Encoded;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") Long id, Model model, Post post){
		model.addAttribute("Post", postService.findById(id));
		model.addAttribute("category", categoryService.findAllCategories());
		return "postEdit";
	}	
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String editPost(Model model, @Valid @ModelAttribute("Post") Post post, BindingResult result,
			@RequestParam("logo2") MultipartFile logo, HttpServletRequest request) throws IOException{
				
		if(result.hasErrors()){	
			model.addAttribute("category", categoryService.findAllCategories());
			return "postEdit";
		}
		
		if(logo.getSize()==0) {
			post.setLogo(postService.findById(post.getId()).getLogo());
		}
		else {			
			post.setLogo(fileToString(logo));  
		}
		postService.updatePost(post);
		
		if(request.isUserInRole("ADMIN")) {
			model.addAttribute("list", postService.findAllPosts());
			return "postListAdmin";
		}

		model.addAttribute("employerBtn", true );
		model.addAttribute("post", post );
		return "postShow";
	}	
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String deletePost(@RequestParam("id") Long id,  Model model, HttpServletRequest request){
		postService.deleteUser(id);
		model.addAttribute("list", postService.findAllPosts());
		
		if(request.isUserInRole("ADMIN")) {
			return "postListAdmin";
		}
		return "redirect:/post/list";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String searchPost(Model model){
		model.addAttribute("Category", new Category());
		model.addAttribute("UserAccount", new UserAccount());
		model.addAttribute("category", categoryService.findAllCategories());
		return "postSearch";
	}
	
	@RequestMapping(value="/searchByAll", method = RequestMethod.GET)
	public String searchByAll(String username, Long categoryId, String city, Model model, 
			@RequestParam(required = false) Integer page){
		
		List<Post> posts;
		Category category = categoryService.findById(categoryId);
		
		//find by city
		if(categoryId == null && username.equals("")) {
			posts = postService.findByCity(city);
			model = makePaginatedList(posts, model, page, 3);		
		}
		//find by category
		else if(city.equals("") && username.equals("")) {		
			Category categoryWithPosts = categoryService.findById(category.getId());
			posts = categoryWithPosts.getPosts();
			model = makePaginatedList(posts, model, page, 3);
		}
		//find by username
		else if(categoryId == null && city.equals("")) {
			posts = postService.findByUsername(username);
			model = makePaginatedList(posts, model, page, 3);
		}
		//search by category and city
		else if(username.equals("")) {
			posts = postService.findByCategoryAndCity(category, city);
			model = makePaginatedList(posts, model, page, 3);
		}
		//find by category and username
		else if(city.equals("")) {
			posts = postService.findByCategoryAndUsername(category, username);
			model = makePaginatedList(posts, model, page, 3);
		}
		//find by city and username
		else if(categoryId == null) {
			posts = postService.findByCityAndUsername(city, username);
			model = makePaginatedList(posts, model, page, 3);
		}
		//search by all
		else {	
			posts = postService.findByCategoryAndCityAndUsername(category, city, username);
			model = makePaginatedList(posts, model, page, 3);			
		}

		
		model.addAttribute("username", username);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("city", city);
		
		return "searchPostList";
	}
	
	
	
	@RequestMapping(value="/postsByUser", method = RequestMethod.GET)
	public String postsByUser(Authentication authentication, Model model){
		
		UserAccount userAccount = userAccountService.findByUsername(authentication.getName());		
		List<Post> posts = postService.findByUsername(userAccount.getUsername());
		model = makePaginatedList(posts, model, new Integer(1), 3);
		model.addAttribute("filter", true);
		return "postList";
	}	
	
	
	@RequestMapping(value="/submitedJobs", method = RequestMethod.GET)
	public String submitedJobs(Model model, Authentication authentication){
		
		UserAccount user = userAccountService.findByUsername(authentication.getName());
		List<Post> posts = user.getPosts();		
		model = makePaginatedList(posts, model, new Integer(1), 3);
		model.addAttribute("filter", true);
		
		return "postList";
	}

	
	@RequestMapping(value= {"","/list"}, method = RequestMethod.GET)
	public String listPosts(Model model, @RequestParam(required = false) Integer page, 
			@RequestParam(required = false) Boolean filter, Authentication authentication, HttpServletRequest request ){
		
		List<Post> posts;
		
		if( filter == null || filter == false ) {
			posts = postService.findAllPosts();
			model = makePaginatedList(posts, model, page, 3);
			model.addAttribute("filter", false);
		}
		else {
			UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
			if( request.isUserInRole("POSLODAVAC") ) {
				posts = postService.findByUsername(userAccount.getUsername());
				model = makePaginatedList(posts, model, page, 3);
				model.addAttribute("filter", true);				
			}
			else { //student
				posts = userAccount.getPosts();	
				model = makePaginatedList(posts, model, page, 3);
				model.addAttribute("filter", true);
			}
			
		}				
		return "postList";
	}
	
	@RequestMapping(value= "/showPost", method = RequestMethod.GET)
	public String showPost(@RequestParam("id") Long id, Model model, Authentication authentication){
		
		Post post = postService.findById(id);
		UserAccount userAccount;
		if(authentication != null) {
			userAccount = userAccountService.findByUsername(authentication.getName());
			model.addAttribute("submited", post.getSubmited().contains(userAccount) );
			
			if( userAccount.getUsername().equals(post.getUsername()) ) { 
				model.addAttribute("employerBtn", true );
				model.addAttribute("count", post.getSubmited().size());
			}
		}
		
		model.addAttribute("post", post );
		return "postShow";
	}
	
	@RequestMapping(value= "/submit", method = RequestMethod.GET)
	public String sign(@RequestParam("id") Long id, Model model, Authentication authentication, RedirectAttributes redirectAttrs){
		
		Post post = postService.findById(id);
		UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
		
		if(!studentDetailsService.findByUserId(userAccount.getId())){
			redirectAttrs.addAttribute("msg", "Molim ispunite detalje prije prijave na posao!" );
			return "redirect:/user/studentDetails";
		}
		
		if(!post.getSubmited().contains(userAccount)) {
			post.getSubmited().add(userAccount);
			postService.addSubmited(post);
		}
		else{
			model.addAttribute("error", "Već ste se prijavili na ovaj oglas" );
		}
		
		model.addAttribute("post", post );
		model.addAttribute("submited", true );

		return "postShow";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/download", produces = MediaType.APPLICATION_PDF_VALUE)
	public void download(HttpServletResponse response, @RequestParam("id") Long id)  throws Exception {

		StudentDetails studentDetails = studentDetailsService.findById(id);
		
		OutputStream out = response.getOutputStream();
	    response.setHeader("Content-Disposition", "download");
	    response.setContentType("application/pdf");
	    FileCopyUtils.copy(studentDetails.getCv(), out);
	    out.flush();
	    out.close();
	    
	}
	
	@RequestMapping(value= "/submitedList", method = RequestMethod.GET)
	public String submitedPost(@RequestParam("id") Long id, Model model, Authentication authentication) 
			throws UnsupportedEncodingException{
		
		Post post = postService.findById(id);
		
		model.addAttribute("post", post );
		Set<UserAccount> userList = post.getSubmited();
		model.addAttribute("userList", userList );
		
		Set<StudentDetails> details = new HashSet<>();
		for (UserAccount user : userList) {
			details.add(studentDetailsService.findById(user.getId()));
		}
		
		model.addAttribute("detailsList", details );
		
		return "showSubmitedUsers";
	}
	
	public Model makePaginatedList(List<Post> list, Model model, Integer page, int size){
		
		PagedListHolder<Post> pagedListHolder = new PagedListHolder<>(list);
		pagedListHolder.setPageSize(size);
		model.addAttribute("maxPages", pagedListHolder.getPageCount() );
		
		if(page==null || page < 1 || page > pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(0);
			model.addAttribute("list", pagedListHolder.getPageList());
		}
		else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            model.addAttribute("list", pagedListHolder.getPageList());
        }
		
		//kratki tekst za prikaz
		for(Post post : pagedListHolder.getPageList()) {
			post.setText(post.getText().substring(0, 10)+"...");
		}
		model.addAttribute("page", page );
		
		return model;
	}
	
}
