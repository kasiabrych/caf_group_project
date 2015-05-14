package ie.cit.caf;

import ie.cit.caf.config.DefaultConfig;
import ie.cit.caf.domain.CHObject;
import ie.cit.caf.domain.Image;
import ie.cit.caf.domain.Images;
import ie.cit.caf.domain.Participant;
import ie.cit.caf.domain.Participation;
import ie.cit.caf.domain.Role;
import ie.cit.caf.fileFinder.FileFinder;
import ie.cit.caf.jparepo.ChoJpaRepo;
import ie.cit.caf.repository.CHORepository;
import ie.cit.caf.repository.ImageRepository;
import ie.cit.caf.repository.ImagesRepository;
import ie.cit.caf.repository.JdbcImagesRepo;
import ie.cit.caf.repository.ParticipantRepository;
import ie.cit.caf.repository.ParticipationRepository;
import ie.cit.caf.repository.RoleRepository;
import ie.cit.caf.rowmapper.CHORowMapper;
import ie.cit.caf.service.CHObjectService;
import ie.cit.caf.service.ParticipantService;
import ie.cit.caf.service.ParticipationService;
import ie.cit.caf.service.RoleService;

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
	ChoJpaRepo choJpaRepo; 
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	CHObjectService choService; 
	@Autowired
	CHORepository choRepository; 
	@Autowired
	RoleRepository roleRepository; 
	@Autowired
	RoleService roleService; 
	@Autowired
	ImageRepository imageRepository; 
	@Autowired
	ImagesRepository imagesRepo; 
	@Autowired 
	ParticipantRepository participantRepository; 
	@Autowired 
	ParticipantService participantService; 
	@Autowired 
	ParticipationRepository participationRepository; 
	@Autowired 
	ParticipationService participationService; 
	
	 public static void main(String[] args) {
	        SpringApplication.run(GroupProjectApplication.class, args);
	    }
	
	 //run method converts .json to java objects and stores object in db
	@Override
	public void run(String... args) throws Exception {
		
		//empty tables
		jdbcTemplate.execute("TRUNCATE TABLE " + "chobjects");
		//jdbcTemplate.execute("TRUNCATE TABLE " + "cho_images");
		jdbcTemplate.execute("TRUNCATE TABLE " + "images");
		jdbcTemplate.execute("TRUNCATE TABLE " + "object_participant_role");
		jdbcTemplate.execute("TRUNCATE TABLE " + "participants");
		jdbcTemplate.execute("TRUNCATE TABLE " + "roles");
		
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
			
			//if (participantRepository.checkIfExist(Integer.parseInt(participant.getPerson_id()))==null){
				participantService.save(participant);
				
			//}
			Role role = p.getRole(); 
			roleService.save(role); 
			//populating the object_participant_role table using service layer
			participationService.saveParticipation(cho, participant, role); 
			
		
		}
		List<Images> imageList = cho.getImages(); 
		for (Images i : imageList){
			imagesRepo.saveImagesWithCHOId(i, cho); 
			//imagesRepo.linkImageToCho(i, cho);
		}
		 
		}
		//calling jpaExample to try out jpa
		jpaExample(); 
		} 

	public void jpaExample(){
		
		long count = choJpaRepo.count(); 
		System.out.println("The count is "+ count);
		
		Iterable<ie.cit.caf.entity.CHObject> list = choJpaRepo.findAll();
		System.out.println(list);
	}

	
}
