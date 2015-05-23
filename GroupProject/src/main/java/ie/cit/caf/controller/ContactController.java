package ie.cit.caf.controller;

import java.util.Date;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableAutoConfiguration
public class ContactController {

	@RequestMapping(value={"/contact"}, method = RequestMethod.GET)
	public String showContactPage(ModelMap model) { 
		Date date = new java.util.Date();		
		model.addAttribute("message", "This is Cooper-Hewit Interactive contact page.");
		model.addAttribute("now", date);
		return "contact";
	} 	
 }	
