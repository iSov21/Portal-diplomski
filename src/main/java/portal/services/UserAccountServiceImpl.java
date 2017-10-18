package portal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import portal.model.UserAccount;
import portal.model.UserDto;
import portal.repositories.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	UserAccountRepository userAccountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<UserAccount> findAllUsers() {
		return userAccountRepository.findAll();
	}

	@Override
	public void saveUser(UserAccount userAccount) {
		userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
		userAccountRepository.save(userAccount);		
	}

	@Override
	public UserAccount findByUsername(String username) {
		return userAccountRepository.findByUsername(username);
	}

	@Override
	public void updateUser(UserAccount userAccount) {
		if(userAccount.getPassword()==null) {
			userAccount.setPassword(userAccountRepository.findById(userAccount.getId()).getPassword());
		}
		else {
			userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
		}
		userAccount.setRole(userAccountRepository.findById(userAccount.getId()).getRole());
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
		novi.setPassword(passwordEncoder.encode(userDto.getPassword()));
		novi.setEmail(userDto.getEmail());
		
		return userAccountRepository.save(novi);
	}
	
	@Override
	public void saveUserRole(UserAccount userAccount) {
		userAccountRepository.save(userAccount);		
	}

}
