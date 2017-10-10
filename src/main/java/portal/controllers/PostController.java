package portal.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import portal.model.Category;
import portal.model.Post;
import portal.model.StudentDetails;
import portal.model.UploadFile;
import portal.model.UserAccount;
import portal.repositories.FileRepository;
import portal.services.CategoryService;
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
	FileRepository fileRepository;
	
	
	@RequestMapping(value= {"","/list"}, method = RequestMethod.GET)
	public String list(Model model){
		List<Post> posts = postService.findAllPosts();
		model.addAttribute("list", posts );
		return "postList";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("Post", new Post());
		model.addAttribute("category", categoryService.findAllCategories());
		return "postAdd";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addUser(Model model, @Valid @ModelAttribute("Post") Post post, BindingResult result, 
			@RequestParam("logo2") MultipartFile logo) throws IOException{
		
		if(result.hasErrors()){	
			model.addAttribute("category", categoryService.findAllCategories());
			return "postAdd";
		}

	    post.setLogo(fileToString(logo));   
	   
		postService.savePost(post);
		model.addAttribute("list", postService.findAllPosts());
		return "postList";
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
			@RequestParam("logo") MultipartFile logo) throws IOException{
		
		if(logo.getSize()==0)
			post.setLogo(postService.findById(post.getId()).getLogo());
		
		/*if(result.hasErrors()){	
			model.addAttribute("category", categoryService.findAllCategories());
			return "postEdit";
		}*/
		post.setLogo(fileToString(logo));  
		
		postService.updatePost(post);
		model.addAttribute("list", postService.findAllPosts());
		return "postList";
	}
	
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String deletePost(@RequestParam("id") Long id,  Model model){
		postService.deleteUser(id);
		model.addAttribute("list", postService.findAllPosts());
		return "postList";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String searchPost(Model model){
		model.addAttribute("Category", new Category());
		model.addAttribute("category", categoryService.findAllCategories());
		return "postSearch";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String searchPosts(Category category, Model model){
		Category categoryWithPosts = categoryService.findById(category.getId());
		model.addAttribute("list", categoryWithPosts.getPosts() );
		return "postList";
	}
	
	@RequestMapping(value="/studentDetails", method = RequestMethod.GET)
	public String studentDetails(Model model, Authentication authentication){
		UserAccount user = userAccountService.findByUsername(authentication.getName());
		StudentDetails details = studentDetailsService.findById(user.getId());
		if( details == null ) {
			StudentDetails newDetails = new StudentDetails();
			newDetails.setUserId(user.getId());
			model.addAttribute("StudentDetails", newDetails);
		}
		else
			model.addAttribute("StudentDetails", details);
		return "studentDetails";
	}
	
	@RequestMapping(value="/studentDetails", method = RequestMethod.POST)
	public String saveDetails(Model model, @ModelAttribute("StudentDetails") StudentDetails studentDetails, 
			@RequestParam("cv2") MultipartFile file) throws Exception{
			
		studentDetails.setCv(file.getBytes());
		
		studentDetailsService.saveDetails(studentDetails);
		model.addAttribute("StudentDetails", studentDetails);
		model.addAttribute("msg", "Detalji spremljeni");
	
		return "studentDetails";
	}
	
	
	@RequestMapping(value="/pagList", method = RequestMethod.GET)
	public String pagList(Model model, @RequestParam(required = false) Integer page){
		List<Post> posts = postService.findAllPosts();
		PagedListHolder<Post> pagedListHolder = new PagedListHolder<>(posts);
		pagedListHolder.setPageSize(3);
		model.addAttribute("maxPages", pagedListHolder.getPageCount() );
		
		if(page==null || page < 1 || page > pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(0);
			model.addAttribute("list", pagedListHolder.getPageList());
		}
		else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            model.addAttribute("list", pagedListHolder.getPageList());
        }
		model.addAttribute("page", page );
		
		return "paginatedPostList";
		// https://stackoverflow.com/questions/31883643/how-do-i-add-simple-pagination-for-spring-mvc	
	}
	
	@RequestMapping(value="/pagList2", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Post> pagList2(Model model, @RequestParam(required = false) Integer page){
		List<Post> posts = postService.findAllPosts();
		PagedListHolder<Post> pagedListHolder = new PagedListHolder<>(posts);
		pagedListHolder.setPageSize(3);
		model.addAttribute("maxPages", pagedListHolder.getPageCount() );
		
		if(page==null || page < 1 || page > pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(0);
			model.addAttribute("list", pagedListHolder.getPageList());
		}
		else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            model.addAttribute("list", pagedListHolder.getPageList());
        }
		model.addAttribute("page", page );
		
		return pagedListHolder.getPageList();
		// https://stackoverflow.com/questions/31883643/how-do-i-add-simple-pagination-for-spring-mvc	
	}
	
	@RequestMapping(value="/pagList22", method = RequestMethod.GET, produces = "application/json")
	public String pagList22(Model model, @RequestParam(required = false) Integer page){
		return "paginatedPostList2";
	}
	
	@RequestMapping(value= "/blogPosts", method = RequestMethod.GET)
	public String listPosts(Model model, @RequestParam(required = false) Integer page){
		List<Post> posts = postService.findAllPosts();
		PagedListHolder<Post> pagedListHolder = new PagedListHolder<>(posts);
		pagedListHolder.setPageSize(3);
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
		return "blogPostList";
	}
	
	@RequestMapping(value= "/show", method = RequestMethod.GET)
	public String showPost(@RequestParam("id") Long id, Model model, Authentication authentication) 
			throws UnsupportedEncodingException{
		
		Post post = postService.findById(id);
		UserAccount userAccount;
		if(authentication != null) {
			userAccount = userAccountService.findByUsername(authentication.getName());
			model.addAttribute("submited", post.getSubmited().contains(userAccount) );
		}
		
		model.addAttribute("post", post );
		return "showPost";
	}
	
	@RequestMapping(value= "/submit", method = RequestMethod.GET)
	public String sign(@RequestParam("id") Long id, Model model, Authentication authentication){
		//provjera jel prijavljen već???
		//provjeri jel ima ispunjene detalje
		
		Post post = postService.findById(id);
		UserAccount userAccount = userAccountService.findByUsername(authentication.getName());
		
		if(!studentDetailsService.findByUserId(userAccount.getId())){
			model.addAttribute("msg", "Molim prvo ispunite detalje!" );
			return "redirect:/post/studentDetails";
		}
		
		if(!post.getSubmited().contains(userAccount)) {
			post.getSubmited().add(userAccount);
			postService.updatePost(post);
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
	
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String submit(@RequestParam("file") MultipartFile file, Model model) throws Exception {
		UploadFile uploadFile = new UploadFile();
		uploadFile.setFileName(file.getOriginalFilename());
        uploadFile.setData(file.getBytes());
        fileRepository.save(uploadFile);
	    model.addAttribute("file", file);
	    
	    byte[] encodeBase64 = Base64.getEncoder().encode(file.getBytes());
	    String base64Encoded = new String(encodeBase64, "UTF-8");
	    model.addAttribute("base",base64Encoded);
	    model.addAttribute("encode",Base64.getEncoder().encode(file.getBytes()));
	    model.addAttribute("decode",Base64.getDecoder().decode(Base64.getEncoder().encode(file.getBytes())));
	    model.addAttribute("image",file.getBytes());
	    
	    
	    return "fileUploadView";
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
		
		Map<String, StudentDetails> details = new HashMap<>();
		for (UserAccount user : userList) {
			details.put(user.getUsername(), studentDetailsService.findById(user.getId()));
		}
		
		model.addAttribute("detailsList", details );
		
		return "showSubmitedUsers";
	}
	
}
