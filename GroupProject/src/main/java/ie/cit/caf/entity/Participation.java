package ie.cit.caf.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="object_participant_role")
public class Participation {
	
	@Id
	public int participation_id;

	@Override
	public String toString() {
		return "Participation [participation_id=" + participation_id + "]";
	} 

}
