package portal.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import portal.model.Category;
import portal.model.Post;
import portal.repositories.PostRepository;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Override
	public List<Post> findAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public void savePost(Post post) {
		post.setCreated(new Date());
		postRepository.save(post);
	}

	@Override
	public void updatePost(Post post) {
		post.setCreated(postRepository.findById(post.getId()).getCreated());
		postRepository.save(post);	
	}

	@Override
	public void deleteUser(Long id) {
		postRepository.delete(id);
	}

	@Override
	public Post findById(Long id) {
		return postRepository.findById(id);
	}

	@Override
	public List<Post> findByCity(String city) {
		return postRepository.findByCity(city);
	}

	@Override
	public List<Post> findByCategoryAndCity(Category category, String city) {
		return postRepository.findByCategoryAndCity(category, city);
	}

	@Override
	public List<Post> findByCategoryOrCity(Category category, String city) {
		return postRepository.findByCategoryOrCity(category, city);
	}
	

}
