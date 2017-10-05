package portal.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "posts")
public class Post {

	private Long id;
	private String username;		
	private Category category;
	private String city;
	private String title;
	private String text;	
	private Date created;
	private String logo;
	private Set<UserAccount> submited = new HashSet<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message="Korisničko ime ne može biti prazno")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	@ManyToOne
	@JoinColumn(name="category")
	@Cascade({CascadeType.SAVE_UPDATE})
	@JsonManagedReference
	public Category getCategory() {
		return category;
	}
	
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@NotEmpty(message="Grad ne može biti prazan")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@NotEmpty(message="Naslov ne može biti prazan")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	@JoinTable(name="post_submit", 
			joinColumns= {@JoinColumn(name="postId", nullable = false, updatable=false) },
			inverseJoinColumns = { @JoinColumn(name="userId", nullable=false, updatable=false) }
	)
	public Set<UserAccount> getSubmited() {
		return submited;
	}
	public void setSubmited(Set<UserAccount> submited) {
		this.submited = submited;
	}

	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
}
