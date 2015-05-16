package ie.cit.caf.controller;

import ie.cit.caf.jparepo.ImagesJpaRepo;
import ie.cit.caf.repository.ImageRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/images")
public class ImageController {
	
	@Autowired
	ImageRepository imageRep;
	
	@Autowired
	ImagesJpaRepo imageJpaRepo;
	
	@RequestMapping(value="/listall", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			
			
			List<ie.cit.caf.domain.Image> listImage=imageRep.findAll();
			model.addAttribute("Images", listImage);
		    return "displayImages";			
		}    
	/*@RequestMapping (value="/medium/like/{mediumName}", method = RequestMethod.GET)
	public String participantByMediumLike(@PathVariable String mediumName, ModelMap model){
		
		List<ie.cit.caf.entity.Participant> choMediumList = participantJpaRepo.findByMediumContains(mediumName);
		
		model.addAttribute("Participants", participantMediumList);
		
		return "displayParticipants";
		
	}*/
		
}                      