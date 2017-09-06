package portal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    private UserDetailsService authenticationService;
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService)/*.passwordEncoder(encoder)*/;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.formLogin()
			.loginPage("/login").permitAll()
		.and()
		.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			//.anyRequest().authenticated()
			//.antMatchers("/baza").authenticated()
			.anyRequest().permitAll()
		.and().csrf().disable();
	}
	
}
