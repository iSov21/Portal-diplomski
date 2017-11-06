package portal.repositories;

import java.util.List;

import portal.model.Post;

public interface PostRepositoryCustom {

	public List<Post> getPostsBySearch(String username, Long categoryId, String city);
}
