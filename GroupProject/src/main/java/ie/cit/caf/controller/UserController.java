package ie.cit.caf.controller;

import ie.cit.caf.entity.User;
import ie.cit.caf.jparepo.UserJpaRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserJpaRepo userJpaRepo; 
	@Autowired
	User user; 
	List<String> interests=new ArrayList <String>();



	@RequestMapping(value="/listall", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {				
		Iterable<User> listUsers=userJpaRepo.findAll(); 
		model.addAttribute("users", listUsers);
		return "displayUsers";			
	}  

//	@RequestMapping(value="/delete", method = RequestMethod.GET) 
//	public String Delete(ModelMap model) {				
//		List<SongwriterImpl> listSongwriters=songwriterDAO.listSongwriters();
//		model.addAttribute("songwriters", listSongwriters);
//		model.addAttribute("greeting", "Please select a particular songwriter to delete");
//		return "delete";			
//	}
//	@RequestMapping(value="/modify", method = RequestMethod.GET) 
//	public String Modify(ModelMap model) {				
//		List<SongwriterImpl> listSongwriters=songwriterDAO.listSongwriters();
//		model.addAttribute("songwriters", listSongwriters);
//		return "modify";			
//	}
//
//	@RequestMapping(value = "/delete/id/{id}", method = RequestMethod.GET)
//	public String deleteSongwriterbyId(@PathVariable int id, ModelMap model) {
//		SongwriterImpl songwriterDelete=songwriterDAO.getSongwriter(id);
//		songwriterDAO.deleteSongwriter(id);
//		model.addAttribute("greeting", "Songwriter with id "+ id +" and details below have been deleted from the system");
//		model.addAttribute("firstname", songwriterDelete.getFirstname());
//		model.addAttribute("lastname", songwriterDelete.getLastname());
//		model.addAttribute("age", songwriterDelete.getAge());
//		return "displaysongwriter";
//	}  
//
//	@RequestMapping(value="/modify/id/{id}/age/{age}", method = RequestMethod.GET)
//	public String modifySongwriter(@PathVariable int id, @PathVariable int age,  ModelMap model) {               
//		songwriterDAO.updateSongwriter(id, age);
//		SongwriterImpl songwriterModify=songwriterDAO.getSongwriter(id);
//		model.addAttribute("message", "Songwriter with id "+ id +" has been modified");
//		model.addAttribute("firstname", songwriterModify.getFirstname());
//		model.addAttribute("lastname", songwriterModify.getLastname());
//		model.addAttribute("age", songwriterModify.getAge());
//		return "displaysongwriter";      
//	}  
//
//	@RequestMapping(value = "/modify/id/{id}", method = RequestMethod.GET)
//	public String modifySongwriter(@PathVariable int id, ModelMap model) {
//		SongwriterImpl songwriterModify=songwriterDAO.getSongwriter(id);
//		model.addAttribute("message", "Songwriter with id "+ id +" can now be modified");
//		model.addAttribute("songwriter", songwriterModify);
//		return "modifyform"; }     
//
//
//
//	@RequestMapping(value="/song/{name}", method = RequestMethod.GET) 
//	public String listSongwriterBySong(@PathVariable("name") String songName, ModelMap model){
//		Date date = new java.util.Date();	
//		List <SongwriterImpl> songwriters=songwriterDAO.listSongWriters(songName);
//		model.addAttribute("songwriters", songwriters);
//		model.addAttribute("now", date);
//		return "displaysongwriters";	
//	}   

	/*@RequestMapping(value="/song/{name}", method=RequestMethod.GET)
		 public String listSongwriterBySong1(@PathVariable String name,  ModelMap model){		
			Date date = new java.util.Date();	
			List<SongwriterImpl> songwriters=songwriterDAO.listSongWriters(name);
			model.addAttribute("songwriters", songwriters);
			model.addAttribute("now", date);
		    return "displaysongwriter";
		}*/

	@RequestMapping(value="/list/{userName}", method=RequestMethod.GET)
	public String listUserByUserName(@PathVariable String userName,  ModelMap model){		
		Date date = new java.util.Date();		
		List<User> users=userJpaRepo.findByUserName(userName); 
		model.addAttribute("users", users);
		model.addAttribute("now", date);
		return "displayUsers";
	}    

	@RequestMapping(value="/list/id/{id}", method=RequestMethod.GET)
	public String listUserByID(@PathVariable int id, ModelMap model){		
		Date date = new java.util.Date();
		List <User> users=new ArrayList<User>();
		User u=userJpaRepo.findOne(id); 
		users.add(u);
		model.addAttribute("users", users);
		model.addAttribute("now", date);
		return "displayUsers";
	} 
//	@RequestMapping(value = "/addNew", method = RequestMethod.GET) 
//	public ModelAndView addNewUser() {   
//		return new ModelAndView("addNewUser", "user", new User());
//	} 
//	
	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
	public String displayUser(@ModelAttribute("user") User user, ModelMap model) {


		model.addAttribute("userName", user.getUserName());
		model.addAttribute("password", user.getPassword()); 
//		
//		if(user.getInterests()!=null && user.getInterests().size()>0){
//            model.addAttribute("interests", user.getInterests());
//        }

		try {
			//int id=songwriterDAO.createSongwriterGetID(songwriter.getFirstname(), songwriter.getFirstname(), songwriter.getAge());
			userJpaRepo.save(user); 
			int id=user.getUserId(); 
			model.addAttribute("userId", Integer.toString(id));
			System.out.println(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "displayUser";
	}        
	@RequestMapping(value = "/addNew", method = RequestMethod.GET) 
	public String addNewSongwriter(ModelMap model) {  

//		List<String> interests=new ArrayList<String>();
//		interests.add("interest1");
//		interests.add("interst2");
//		user.setInterests(interests);	
		model.addAttribute("user", user);		
		return "addNewUser";
	} 
	@RequestMapping(value = "/delete", method = RequestMethod.GET) 
	public String deleteUser(ModelMap model) {   
		Iterable <User> users=userJpaRepo.findAll();
		model.addAttribute("users", users);		
		return "delete";
	} 
	@RequestMapping(value = "/delete/id/{id}", method = RequestMethod.GET) 
	public String deleteSongwriterbyId(@PathVariable int id, ModelMap model) { 
		User userDelete = userJpaRepo.findOne(id); 
		userJpaRepo.delete(userDelete);
		//userJpaRepo.delete(id);
		model.addAttribute("greeting", "User with id "+ id +" and details below have been deleted from the system");
		model.addAttribute("userName", userDelete.getUserName());
		model.addAttribute("password", userDelete.getPassword());
		model.addAttribute("userId", userDelete.getUserId());
		return "displayUser";
	} 
	@RequestMapping(value="/modify", method = RequestMethod.GET) 
	public String modify(ModelMap model) {			
		List<User> users=userJpaRepo.findAll();
		model.addAttribute("users", users);
		return "modify";			
	}  
	@RequestMapping(value = "/modify/id/{id}", method = RequestMethod.GET) 
	public String modifyUser(@PathVariable int id, ModelMap model) { 
		User userModify = userJpaRepo.findOne(id); 
		model.addAttribute("user", userModify);
		return "modifyForm";	} 	
	
	@RequestMapping(value="/modify/id/{id}/password/{password}", method = RequestMethod.GET) 
	public String modifySongwriter(@PathVariable int id, @PathVariable String password,  ModelMap model) {			
		//userJpaRepo.updatePassword(id, password); 
		User userM = userJpaRepo.findOne(id); 
		userM.setPassword(password);
		userJpaRepo.save(userM); 
		model.addAttribute("message", "User with id "+ id +" has been modified");
		model.addAttribute("userName", userM.getUserName());
		model.addAttribute("password", userM.getPassword());
		model.addAttribute("userId", userM.getUserId());
		return "displayUser";		
	}      
//
//	@RequestMapping(value = "/addNew", method = RequestMethod.POST)
//	public String displaySongwriter(@ModelAttribute("songwriter") @Valid SongwriterImpl songwriter,  
//			BindingResult result,
//			ModelMap model) {
//
//		if(result.hasErrors())
//			return "addnewsongwriter";
//
//		model.addAttribute("firstname", songwriter.getFirstname());
//		model.addAttribute("lastname", songwriter.getFirstname());
//		model.addAttribute("age", songwriter.getAge());	
//
//		if(songwriter.getInterests()!=null && songwriter.getInterests().size()>0){
//			model.addAttribute("interests", songwriter.getInterests());
//		}
//
//		try {
//			int id=songwriterDAO.createSongwriterGetID(songwriter.getFirstname(), songwriter.getLastname(), songwriter.getAge());
//			//songwriter.setId(id);
//			model.addAttribute("id", id);
//			model.addAttribute("greeting", "Songwriter with the following details has been successfully added");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "displaysongwriter";
//	}  
//}
//

}
