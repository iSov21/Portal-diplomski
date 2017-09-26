package portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portal.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	UserAccount findByUsername(String username);

	UserAccount findById(Long id);

	UserAccount findByEmail(String email);
}
