package portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.model.EmployerDetails;

@Repository
public interface EmployerDetailsRepository extends JpaRepository<EmployerDetails, Long> {

	EmployerDetails findByUserId(Long UserId);
	
}
