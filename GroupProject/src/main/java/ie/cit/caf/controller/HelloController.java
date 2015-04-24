package ie.cit.caf.controller;

import java.util.Date;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloController {
	
	 @RequestMapping(value={"/","/home"})
		public String showHomePage(ModelMap model) { 
			Date date = new java.util.Date();		
			model.addAttribute("message", "This is TheHits home page.");
			model.addAttribute("now", date);
			return "hello";
		} 	
	 }	  