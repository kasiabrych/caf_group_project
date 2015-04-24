package ie.cit.caf.controller;

import java.util.List;

import ie.cit.caf.domain.CHObject;
import ie.cit.caf.repository.CHORepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cho")
public class CHOController {
	
	@Autowired
	CHORepository choRep;
	
	@RequestMapping(value="/listall", method = RequestMethod.GET) 
	public String listAll(ModelMap model) {			
			
			List<CHObject> listCHO=choRep.findAll();
			model.addAttribute("CHOs", listCHO);
		    return "displayCHO";			
		}    
		
}                      