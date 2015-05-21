package ie.cit.caf.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int userId; 
	public String userName; 
	public String password;
//	@ElementCollection
//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinTable(name="interests",
//			joinColumns={@JoinColumn(name="userId", referencedColumnName="userId")},
//			inverseJoinColumns={@JoinColumn(name="interestId", referencedColumnName="interests")})
//	private List<Interests> interests;
	private Boolean newsletter;
	
	
	public User() {
		super();
	}
	public User(int userId, String userName, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + "]";
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public List<Interests> getInterests() {
//		return interests;
//	}
//	public void setInterests(List<String> interests2) {
//		this.interests = interests2;
//	}
	public Boolean getNewsletter() {
		return newsletter;
	}
	public void setNewsletter(Boolean newsletter) {
		this.newsletter = newsletter;
	}
	
}
