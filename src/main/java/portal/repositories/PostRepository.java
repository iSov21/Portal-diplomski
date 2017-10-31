package portal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.model.Category;
import portal.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	Post findById(Long id);

	List<Post> findByCity(String city);
	
	List<Post> findByCategoryAndCity(Category category, String city);

	List<Post> findByCategoryOrCity(Category category, String city);

	List<Post> findByUsername(String username);

	List<Post> findByCategoryAndCityAndUsername(Category category, String city, String username);

	List<Post> findByCategoryAndUsername(Category category, String username);

	List<Post> findByCityAndUsername(String city, String username);
}
