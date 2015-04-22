package ie.cit.caf;

import ie.cit.caf.config.DefaultConfig;
import ie.cit.caf.domain.CHObject;
import ie.cit.caf.domain.Image;
import ie.cit.caf.domain.Participant;
import ie.cit.caf.domain.Participation;
import ie.cit.caf.domain.Role;
import ie.cit.caf.fileFinder.FileFinder;
import ie.cit.caf.repository.CHORepository;
import ie.cit.caf.repository.ImageRepository;
import ie.cit.caf.repository.ParticipantRepository;
import ie.cit.caf.repository.ParticipationRepository;
import ie.cit.caf.repository.RoleRepository;
import ie.cit.caf.rowmapper.CHORowMapper;
import ie.cit.caf.service.CHObjectService;
import ie.cit.caf.service.ParticipationService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/*
 * author Kasia Brych (R00048777)
 * AssignmentApplication class: 
 * deserialises .json files into java objects and stores them in the assignment database
 * 
 */
@SpringBootApplication
@ActiveProfiles ("default")
@Import(DefaultConfig.class)
public class GroupProjectApplication implements CommandLineRunner{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	CHObjectService choService; 
	@Autowired
	CHORepository choRepository; 
	@Autowired
	RoleRepository roleRepository; 
	@Autowired
	ImageRepository imageRepository; 
	@Autowired 
	ParticipantRepository participantRepository; 
	@Autowired 
	ParticipationRepository participationRepository; 
	@Autowired 
	ParticipationService participationService; 
	
	//those were set up in an attempt to add populate the participants table without duplicates
	final static Set <Participant> allPart = new HashSet <Participant>();
	Iterator<Participant> iterator = allPart.iterator(); 
	
	 public static void main(String[] args) {
	        SpringApplication.run(GroupProjectApplication.class, args);
	    }
	
	 //run method converts .json to java objects and stores object in db
	@Override
	public void run(String... args) throws Exception {
		
		 String choFile = args[0]; 
		 System.out.printf("Processing file %s...\n", choFile); 
	
		List <Path> files = FileFinder.getFileList(choFile, "*.json"); 
		for (Path f : files){
		CHObject cho = new ObjectMapper().readValue(f.toFile(), CHObject.class); 
		System.out.println("\n" + cho.toString()); 
		//saving CHObjects using service layer
		choService.save(cho);
		List<Participation> partList = cho.getParticipations(); 
		 
		for (Participation p : partList) {
			Participant participant = p.getParticipant(); 
//			checkIfExists(participant); 
			//the above method was meant to check if participant has already been added, 
			//it does not work
			
//			saveAllPart(allPart);
			Role role = p.getRole(); 
//			if (participant.getParticipant_id() ==0); 
			participantRepository.saveInsert(participant);

//			if (role.getRole_no() ==0)
			roleRepository.save(role); 
			//populating the object_participant_role table using service layer
			participationService.saveParticipation(cho, participant, role); 
			
		
		}
		List<Map<String, Image>> imageList = cho.getImages(); 
		for (Map<String, Image> map : imageList){
			for (Map.Entry<String, Image> entry : map.entrySet()){
				Image i = entry.getValue(); 
				imageRepository.save(i);
				imageRepository.linkImageToCho(i, cho); 
			}
		}
		 
		}
		//the code below was meant to ensure that a participant was added only once, 
		//it does not work
		//saveAllPart(allPart);
		} 
//private void checkIfExists(Participant participant) {
//	if (!allPart.isEmpty()){
//the use of iterator was attempted because for loop was throwing java.util.ConcurrentModificationException
//		while (iterator.hasNext()){
//			
//		Participant par = iterator.next(); 
//		
//			if (participant.getPerson_id().equalsIgnoreCase(par.getPerson_id())){
//				System.out.println("Existing participant");
//			}else
//				allPart.add(participant); 
//			
//		}		
//		}else
//			allPart.add(participant); 
//		
//	}
//the method below was also meant to prevent duplication, unsuccessfully
//private void saveAllPart(Set<Participant> allPart) {
//	for (Participant par : allPart){
////		if (par.getParticipant_id() !=0){
////		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!"+par.getPerson_name() +par.getParticipant_id());	
////		}else
//		participantRepository.saveInsert(par);
//		//System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~"+par.getPerson_name()+par.getParticipant_id());
//	}
//	System.out.println(allPart.size());
//}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
///Ignore methods below, they were used initially to test db connectivity and classes//////////
	public void query01() {
		// Query for a list of maps with key-value pairs
		// The hard way!!!
			
		System.out.println("\nQuery 1 (List all artists using resultset Map)\n----------------");
			
		String sql = "SELECT * FROM chobjects";
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(sql);
			
		for (Map<String, Object> row : resultSet) {
			System.out.println("Name: " + row.get("id"));
			System.out.println("ID: " + row.get("title"));
			System.out.println("Gender: " + row.get("date") + "\n");
		}
	}
	public void query02() {
		// Query for a list of objects - automatic mapping from row to object using RowMapper class
		// Using parameterised "prepared statements" reduces the risk of a SQL inject attack
			
		System.out.println("\nQuery 2 (List male artists using RowMapper)\n-----------------");
			
		String sql = "SELECT * FROM chobjects WHERE id = ?";
		List<CHObject> chobjects = jdbcTemplate.query(sql, new Object[] { "68268203" }, new CHORowMapper());
			
		for (CHObject artist : chobjects) {
			System.out.println(artist.toString());
		}
	}
	public void repositoryExample(){
		CHObject chobject = new CHObject(); 
		chobject.setId(71);
		chobject.setTitle("break");
		chobject.setDate("taday");
		chobject.setMedium("glass"); 
		chobject.setCreditline("no"); 
		chobject.setDescription("low");
		chobject.setGallery_text("No text");
		
		choRepository.save(chobject);
		
		CHObject cho2 = choRepository.get(80); 
		System.out.println("CHO added"); 
		System.out.println(cho2.toString());
		
	}

	
}
