package orser.springboot.presidents.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import orser.springboot.presidents.entity.President;
import orser.springboot.presidents.service.CandidateService;
import orser.springboot.presidents.service.PresidentService;

@Controller
@RequestMapping("/presidents")
public class PresidentController {

	private PresidentService presidentService;
	private CandidateService candidateService;
	
	public PresidentController(PresidentService thePresidentService, CandidateService theCandidateService) {
		presidentService = thePresidentService;
		candidateService = theCandidateService;
	}
	
	// add mapping for "/list"
	@GetMapping("/list")
	public String listPresidents(Model theModel) {
		
		List<President> thePresidents = presidentService.findAll();
		
		theModel.addAttribute("presidents", thePresidents);
		
		return "presidents/list-presidents";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute
		President thePresident = new President();
		
		theModel.addAttribute("president", thePresident);
		
		return "presidents/president-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("presidentId") int theId, Model theModel) {
		
		// get president from service
		President thePresident = presidentService.findById(theId);
		
		// set president as a model attribute to pre-populate form
		theModel.addAttribute("president", thePresident);
		
		// send over to form
		return "presidents/president-form";
	}
	
	@PostMapping("/save")
	public String savePresident(@ModelAttribute("president") President thePresident) {
		
		// save the president
		presidentService.save(thePresident);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/presidents/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("presidentId") int theId) {
		
		// delete the president
		presidentService.deleteById(theId);
		
		// redirect
		return "redirect:/presidents/list";
	}
	
	@GetMapping("/flipElection")
	public String flipElection(@RequestParam("startOfTerm") @DateTimeFormat(iso = ISO.DATE) LocalDate startOfTerm) {
		
		// insert candidate
		presidentService.addCandidate(startOfTerm);
		
		// redirect
		return "redirect:/presidents/list";
	}
	
	@GetMapping("/resetDatabase")
	public String resetDatabase() {
		
		// reset database
		presidentService.resetDatabase();
		
		// redirect
		return "redirect:/presidents/list";
	}
	
}
