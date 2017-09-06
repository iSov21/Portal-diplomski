package portal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import portal.model.UserAccount;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserAccountService userAccountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		UserAccount userAccount = userAccountService.findByUsername(username);

		if (userAccount == null) {
			throw new UsernameNotFoundException("Username not found: " + username);
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		User user = new User(userAccount.getUsername(), userAccount.getPassword(), authorities);
		return user;
	}

}
