package portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.model.Post;
import portal.model.StudentDetails;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Long> {

	StudentDetails findByUserId(Long UserId);
}
