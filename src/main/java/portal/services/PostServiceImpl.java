package portal.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public void updatePost(Post postVO) {
		Post post = postRepository.findById(postVO.getId());
		postVO.setCreated(post.getCreated());
		postVO.setSubmited(post.getSubmited());
		postRepository.save(postVO);	
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
	public List<Post> findByUsername(String username) {
		return postRepository.findByUsername(username);
	}

	@Override
	public void addSubmited(Post post) {
		postRepository.save(post);
		
	}

	@Override
	public List<Post> getPostsBySearch(String username, Long categoryId, String city) {
		return postRepository.getPostsBySearch(username, categoryId, city);
	}
	

}
