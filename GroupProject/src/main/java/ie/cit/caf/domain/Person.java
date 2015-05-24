package ie.cit.caf.domain;
public class Person {

	import javax.validation.constraints.Min;
	import javax.validation.constraints.NotNull;
	import javax.validation.constraints.Size;

	public class Person {

	    @Size(min=2, max=30)
	    private String username;

	    @NotNull
	    @Size(min=2, max=30)
	    private String password;;

	    public String getUsername() {
	        return this.username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public Integer getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String toString() {
	        return "Person(Username: " + this.username + ", Password: " + this.password + ")";
	    }

	}	
	
	
}
