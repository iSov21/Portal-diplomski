package portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	Post findById(Long id);
}
