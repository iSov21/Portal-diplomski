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
import org.springframework.transaction.annotation.Transactional;

import portal.model.UserAccount;

@Service
@Transactional
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
		authorities.add(new SimpleGrantedAuthority("ROLE_"+userAccount.getRole()));
		User user = new User(userAccount.getUsername(), userAccount.getPassword(), authorities);
		return user;
	}
	
	//ili tu sa mailom ovo: 
	/*@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		
		UserAccount userAccount = userAccountService.findByUsername(email);

		if (userAccount == null) {
			throw new UsernameNotFoundException("Username not found: " + email);
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		User user = new User(userAccount.getUsername(), userAccount.getPassword(), authorities);
		return user;
	}*/
}
