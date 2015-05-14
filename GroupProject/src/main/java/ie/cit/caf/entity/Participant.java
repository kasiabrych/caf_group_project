package ie.cit.caf.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="participants")
public class Participant implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int participant_id; 
	private String person_id; 
	private String person_name; 
	private String person_date; 
	private String person_url;

	@Override
	public String toString() {
		return "Participant [participant_id=" + participant_id + ", person_id="
				+ person_id + ", person_name=" + person_name + ", person_date="
				+ person_date + ", person_url=" + person_url + "]";
	}
	public int getParticipant_id() {
		return participant_id;
	}
	public void setParticipant_id(int participant_id) {
		this.participant_id = participant_id;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public String getPerson_date() {
		return person_date;
	}
	public void setPerson_date(String person_date) {
		this.person_date = person_date;
	}
	public String getPerson_url() {
		return person_url;
	}
	public void setPerson_url(String person_url) {
		this.person_url = person_url;
	}
	
}
