package ie.cit.caf.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="chobjects")
public class CHObject {
	
	@Id
	private int id; 
	private String title;
	private String date; 
	private String medium; 
	private String creditline;
	private String description;
	private String gallery_text;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name="object_participant_role",
			joinColumns={@JoinColumn(name="object_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="participation_id", referencedColumnName="participation_id")})
	private List<Participation> participations; 
	
//	@OneToMany(fetch = FetchType.EAGER)
//	@JoinTable(name="cho_images",
//			joinColumns={@JoinColumn(name="cho_id", referencedColumnName="id")},
//			inverseJoinColumns={@JoinColumn(name="image_no", referencedColumnName="image_no")})
//	public List<Map<String, Image>> images; 

	
	@Override
	public String toString() {
		return "CHObject [id=" + id + ",\n title=" + title + ",\n date=" + date
				+ ",\n medium=" + medium + ",\n creditline=" + creditline
				+ ",\n description=" + description + ",\n gallery_text="
				+ gallery_text + ",\n participations=" + participations + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getCreditline() {
		return creditline;
	}
	public void setCreditline(String creditline) {
		this.creditline = creditline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGallery_text() {
		return gallery_text;
	}
	public void setGallery_text(String gallery_text) {
		this.gallery_text = gallery_text;
	}
//	public List<Map<String, Image>> getImages() {
//		return images;
//	}
//	public void setImages(List<Map<String, Image>> images) {
//		this.images = images;
//	}
	//	@JsonUnwrapped
//	@JsonProperty ("participants")
//	private List<Participation> participations; 
	public List<Participation> getParticipations() {
		return participations;
	}
	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}
	
}
