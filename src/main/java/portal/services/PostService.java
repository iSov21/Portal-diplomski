package portal.services;

import java.util.List;

import portal.model.Category;
import portal.model.Post;

public interface PostService {

	public List<Post> findAllPosts();

	public void savePost(Post post);

	public void updatePost(Post post);

	public void deleteUser(Long id);

	public Post findById(Long id);

	public List<Post> findByCity(String city);

	public List<Post> findByCategoryAndCity(Category category, String city);

	//public List<Post> findByCategoryOrCity(Category category, String city);
	
	public List<Post> findByCategoryAndCityAndUsername(Category category, String city, String username);

	public List<Post> findByUsername(String username);

	public void addSubmited(Post post);

	public List<Post> findByCategoryAndUsername(Category category, String username);

	public List<Post> findByCityAndUsername(String city, String username);

	
}
