package portal.services;

import java.util.List;

import portal.model.Category;

public interface CategoryService {

	public List<Category> findAllCategories();

	public Category findById(Long id);
	
}
