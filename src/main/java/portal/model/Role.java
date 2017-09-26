package portal.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="role")
public class Role {


	private Long id;
	private String name;
	private String description;
	private Set<UserAccount> users = new HashSet<>();
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
	@JsonBackReference
	public Set<UserAccount> getUsers() {
		return users;
	}
	public void setUsers(Set<UserAccount> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	
}
