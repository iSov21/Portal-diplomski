package portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findById(Long id);
}
