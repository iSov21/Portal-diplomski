package portal.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import portal.model.Post;

@Repository
@Transactional(readOnly = true)
public class PostRepositoryImpl implements PostRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Post> getPostsBySearch(String username, Long categoryId, String city){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Post> criteria = builder.createQuery(Post.class);
		Root<Post> pRoot = criteria.from(Post.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!"".equals(username)) {
			predicates.add(builder.equal(pRoot.get("username"), username));
		}
		
		if(!"".equals(city)) {
			predicates.add(builder.equal(pRoot.get("city"), city));
		}
		
		if(categoryId != null) {
			predicates.add(builder.equal(pRoot.get("category"), categoryId));
		}
		
		criteria.select(pRoot).where(predicates.toArray(new Predicate[]{}));
		return entityManager.createQuery(criteria).getResultList();
				
	}
}
