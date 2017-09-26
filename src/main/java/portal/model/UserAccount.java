package portal.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user")
public class UserAccount {


	private Long id;
	private String username;
	private String password;
	private String email;
	private Role role;
	private Set<Post> posts = new HashSet<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Korisničko ime ne može biti prazno")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotEmpty(message = "Lozinka ne može biti prazna")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotEmpty(message = "Email ne može biti prazan")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@ManyToOne
	@JoinColumn(name="role")
	@Cascade({CascadeType.SAVE_UPDATE})
	@JsonManagedReference
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "submited")
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}


	protected final int hash(Object object) {
		if (object == null)
			return 0;
		return object.hashCode();
	}
	
	public int hashCode() {
		int hash = 13;
		hash += hash(getUsername());
		return hash;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (!(o instanceof UserAccount))
			return false;
		UserAccount other = (UserAccount) o;
		if ( !username.equals(other.getUsername()))
			return false;
		return true;
	}
	
}
