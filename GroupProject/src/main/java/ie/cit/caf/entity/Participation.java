package ie.cit.caf.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="object_participant_role")
public class Participation {
	
	@Id
	public int participation_id;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name="object_participant_role",
			joinColumns={@JoinColumn(name="participation_id", referencedColumnName="participation_id")},
			inverseJoinColumns={@JoinColumn(name="person_id", referencedColumnName="person_id")})
	private List<Participant> participants; 
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name="object_participant_role",
			joinColumns={@JoinColumn(name="participation_id", referencedColumnName="participation_id")},
			inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="role_id")})
	private List<Role> roles;

	@Override
	public String toString() {
		return "Participation\n [\nparticipation_id=" + participation_id
				+ ",\n participants=" + participants + ",\n roles=" + roles + "]";
	} 

}
