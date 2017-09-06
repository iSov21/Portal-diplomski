package portal.services;

import java.util.List;

import portal.model.Post;

public interface PostService {

	public List<Post> findAllPosts();

	public void savePost(Post post);

	public void updatePost(Post post);

	public void deleteUser(Long id);

	public Post findById(Long id);
	
}
