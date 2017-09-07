package portal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import portal.model.Category;
import portal.model.Post;
import portal.services.CategoryService;
import portal.services.PostService;

@Controller
@RequestMapping(value="/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
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
	public String addUser(Model model, Post post){
		postService.savePost(post);
		model.addAttribute("list", postService.findAllPosts());
		return "postList";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") Long id, Model model, Post post){
		model.addAttribute("Post", postService.findById(id));
		model.addAttribute("category", categoryService.findAllCategories());
		return "postEdit";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String editPost(Model model, Post post){
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
	
}
