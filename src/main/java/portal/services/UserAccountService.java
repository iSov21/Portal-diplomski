package portal.services;

import java.util.List;


import portal.model.UserAccount;

public interface UserAccountService {

	public List<UserAccount> findAllUsers();
	
	public void saveUser(UserAccount user);

	public UserAccount findByUsername(String username);

	public void updateUser(UserAccount userAccount);

	public void deleteUser(Long id);

	public UserAccount findById(Long id);
}
