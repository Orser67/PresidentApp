package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entities.President;
import service.PresidentService;

@Controller
@RequestMapping("/president")
public class PresidentController {

	@Autowired
	private PresidentService presidentService;
	
	@GetMapping("/list")
	public String listPresidents(Model theModel) {
		
		// get presidents from the service
		List<President> thePresidents = presidentService.getPresidents();
				
		// add the presidents to the model
		theModel.addAttribute("presidents", thePresidents);
		
		return "list-presidents";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		President thePresident = new President();
		
		theModel.addAttribute("president", thePresident);
		
		return "president-form";
	}
	
	@PostMapping("/savePresident")
	public String savePresident(@ModelAttribute("president") President thePresident) {
		
		// save the president using our service
		presidentService.savePresident(thePresident);	
		
		return "redirect:/president/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("presidentId") int theId,
									Model theModel) {
		
		// get the president from our service
		President thePresident = presidentService.getPresident(theId);	
		
		// set president as a model attribute to pre-populate the form
		theModel.addAttribute("president", thePresident);
		
		// send over to our form		
		return "president-form";
	}
	
	@GetMapping("/delete")
	public String deletePresident(@RequestParam("presidentId") int theId) {
		
		// delete the president
		presidentService.deletePresident(theId);
		
		return "redirect:/president/list";
	}
}










