package portal.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
	
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Model model, @RequestParam(required = false) Integer page){
		List<Post> posts = postService.findAllPosts();
		
		model = makePaginatedList(posts, model, page, 6);
		return "postList";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String add(Model model, Authentication authentication, RedirectAttributes redirectAttrs){
		
		UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
		
		if(!employerDetailsService.findByUserId(userAccount.getId())){
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
			return "postList";
		}
		return "redirect:/post/blogPosts";
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
			return "postList";
		}

		model.addAttribute("employerBtn", true );
		model.addAttribute("post", post );
		return "showPost";
	}	
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String deletePost(@RequestParam("id") Long id,  Model model, HttpServletRequest request){
		postService.deleteUser(id);
		model.addAttribute("list", postService.findAllPosts());
		
		if(request.isUserInRole("ADMIN")) {
			return "postList";
		}
		return "redirect:/post/blogPosts";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String searchPost(Model model){
		model.addAttribute("Category", new Category());
		model.addAttribute("UserAccount", new UserAccount());
		model.addAttribute("category", categoryService.findAllCategories());
		return "postSearch";
	}
	
	@RequestMapping(value="/searchByCategoryAndCity", method = RequestMethod.GET)
	public String searchByCategoryAndCity(Long categoryId, String city, Model model, @RequestParam(required = false) Integer page){
			
		if(categoryId == null) {
			List<Post> list = postService.findByCity(city);
			model = makePaginatedList(list, model, page, 3);		
		}
		else if(city.equals("") ) {
			Category category = categoryService.findById(categoryId);
			Category categoryWithPosts = categoryService.findById(category.getId());
			List<Post> list = categoryWithPosts.getPosts();
			model = makePaginatedList( list, model, page, 3);
		}
		else {
			Category category = categoryService.findById(categoryId);
			List<Post> list = postService.findByCategoryAndCity(category, city);
			model = makePaginatedList(list, model, page, 3);
		}
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("city", city);
		model.addAttribute("cSearch", true);
		return "searchBlogPostList";
	}
	
	@RequestMapping(value="/searchByUser", method = RequestMethod.GET)
	public String searchByUser(String username, Model model, @RequestParam(required = false) Integer page){
		
		List<Post> list = postService.findByUsername(username);
		model = makePaginatedList(list, model, page, 3);
		model.addAttribute("username", username);
		model.addAttribute("userSearch", true);
		return "searchBlogPostList";
	}
	
	
	@RequestMapping(value="/postsByUser", method = RequestMethod.GET)
	public String postsByUser(Authentication authentication, Model model){
		
		UserAccount userAccount = userAccountService.findByUsername(authentication.getName());		
		List<Post> list = postService.findByUsername(userAccount.getUsername());
		model = makePaginatedList(list, model, new Integer(1), 3);
		model.addAttribute("filter", true);
		return "blogPostList";
	}	
	
	
	@RequestMapping(value="/submitedJobs", method = RequestMethod.GET)
	public String submitedJobs(Model model, Authentication authentication){
		
		UserAccount user = userAccountService.findByUsername(authentication.getName());
		List<Post> list = user.getPosts();		
		model = makePaginatedList(list, model, new Integer(1), 3);
		model.addAttribute("filter", true);
		
		return "blogPostList";
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
	

	
	@RequestMapping(value= "/blogPosts", method = RequestMethod.GET)
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
					
					List<Long> ids = new ArrayList<Long>();
					for( Post post : posts ) {
						if( post.getSubmited().contains(userAccount) )
							ids.add(post.getId());
					}
					
					model.addAttribute("idovi", ids);
				}
				else { //student
					posts = postService.findByUsername(userAccount.getUsername());
					model = makePaginatedList(posts, model, page, 6);
					model.addAttribute("filter", true);
				}
			
		}				
		return "blogPostList";
	}
	
	@RequestMapping(value= "/showPost", method = RequestMethod.GET)
	public String showPost(@RequestParam("id") Long id, Model model, Authentication authentication) 
			throws UnsupportedEncodingException{
		
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
		return "showPost";
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
		
		Set<UserAccount> userList = post.getSubmited();
		model.addAttribute("userList", userList );
		return "showPost";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/download", produces = MediaType.APPLICATION_PDF_VALUE)
	public void download(HttpServletResponse response, @RequestParam("id") Long id)  throws Exception {

		StudentDetails studentDetails = studentDetailsService.findById(id);
		
		OutputStream out = response.getOutputStream();
	    response.setHeader("Content-Disposition", "download");
	    //response.setContentType("image/png");
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
	
}
