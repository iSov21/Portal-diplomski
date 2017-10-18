package portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portal.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findById(Long id);

}
