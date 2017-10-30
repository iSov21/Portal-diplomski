package portal.model;

import org.hibernate.validator.constraints.NotEmpty;

public class UserDto {
    
	@NotEmpty(message = "Korisničko ime ne može biti prazno")
    private String username;
     

	@NotEmpty(message = "Lozinka ne može biti prazna")
    private String password;
    private String matchingPassword;
     
    @NotEmpty(message = "Email ne može biti prazan")
    private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
