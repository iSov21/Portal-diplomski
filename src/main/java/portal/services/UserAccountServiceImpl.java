package portal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import portal.model.UserAccount;
import portal.model.UserDto;
import portal.repositories.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	UserAccountRepository userAccountRepository;
	
	@Override
	public List<UserAccount> findAllUsers() {
		return userAccountRepository.findAll();
	}

	@Override
	public void saveUser(UserAccount user) {
		userAccountRepository.save(user);		
	}

	@Override
	public UserAccount findByUsername(String username) {
		return userAccountRepository.findByUsername(username);
	}

	@Override
	public void updateUser(UserAccount userAccount) {
		userAccountRepository.save(userAccount);	
	}

	@Override
	public void deleteUser(Long id) {
		userAccountRepository.delete(id);
	}

	@Override
	public UserAccount findById(Long id) {
		return userAccountRepository.findById(id);
	}

	@Override
	public UserAccount register(UserDto userDto) {
		UserAccount userAccount = userAccountRepository.findByEmail(userDto.getEmail());
		
		if(userAccount != null) {
            return null;
        }
		
		UserAccount novi = new UserAccount();
		novi.setUsername(userDto.getUsername());
		novi.setPassword(userDto.getPassword());
		novi.setEmail(userDto.getEmail());
		
		return userAccountRepository.save(novi);
	}

}
