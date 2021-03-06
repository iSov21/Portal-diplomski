package portal.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    private UserDetailsService authenticationService;
	
    @Autowired
    private PasswordEncoder encoder;
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(encoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.formLogin()
			.loginPage("/login").permitAll()
		.and()
		.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/admin/**", "/post/listAdmin","/user/addRole", "/user/add", "/user/edit", "/user/delete").hasRole("ADMIN")
			.antMatchers("/post/add", "/post/edit", "/post/delete").hasAnyRole("POSLODAVAC", "ADMIN")
			.antMatchers("/postsByUser", "/submitedList", "user/employerDetails").hasRole("POSLODAVAC")
			.antMatchers("/submitedJobs", "/submit", "/user/studentDetails").hasRole("STUDENT")			
			.anyRequest().permitAll()
		.and()
			.exceptionHandling().accessDeniedPage("/accessDenied.jsp")
		.and().csrf().disable();
	}
	
}
