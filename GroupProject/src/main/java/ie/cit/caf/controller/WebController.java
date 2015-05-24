package ie.cit.caf.controller;
public class WebController {

	
	import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.servlet.ModelAndView;



	public class LoginController {
	                         
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(
	       @RequestParam(value = "error", required = false) String error,
	       @RequestParam(value = "logout", required = false) String logout) {
	             
	       ModelAndView model = new ModelAndView();
	       if (error != null) {
	                     model.addObject("error", "Invalid username and password!");
	              }
	      
	       if (logout != null) {
	              model.addObject("msg", "You've been logged out successfully.");
	       }
	       model.setViewName("login");
	      
	       return model;
	}
	}

	
	
	import javax.validation.Valid;

	import org.springframework.stereotype.Controller;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.servlet.mvc.support.RedirectAttributes;
	import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
	import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


	@Controller
	public class WebController extends WebMvcConfigurerAdapter {

	    @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/results").setViewName("results");
	    }

	    @RequestMapping(value="/", method=RequestMethod.GET)
	    public String showForm(Person person) {
	        return "form";
	    }

	    @RequestMapping(value="/", method=RequestMethod.POST)
	    public String checkPersonInfo(@Valid Person person, BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            return "form";
	        }
	        return "redirect:/results";
	    }

	}	
	
}
