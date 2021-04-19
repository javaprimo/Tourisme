package com.example.demo.web;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Region;
import com.example.demo.entities.Section;
import com.example.demo.entities.Site;
import com.example.demo.services.RegionService;
import com.example.demo.services.SectionService;
import com.example.demo.services.SiteService;
@Controller
@RequestMapping("/sections")
public class SectionControleur {
	@Autowired
	private SectionService sectionService;
	@Autowired
	private SiteService siteService;
	
	@RequestMapping("/index")
	public String index(Model modele) {
		List<Section> sections=sectionService.sections();
    	modele.addAttribute("sections",sections);
		return "IndexSection";
	}
	
    @RequestMapping("/FormSection")
	public String formSection(Model modele) {
    	List<Site> sites = siteService.sites();
    	modele.addAttribute("sites",sites);
    	modele.addAttribute("section",new Section());
		return "formulaireSection";
	}
    
	  @RequestMapping(value = "/FormSection", method = RequestMethod.POST)
		public String sauverSection(@Valid Section s,Model modele, Errors errors, @RequestParam(name = "site") int reference) {
	  	if(errors.hasErrors()) {
	  		return "formulaireSection";
	  	}
	  	Site site = siteService.UnSite(reference);
	  	s.setSite(site);
    	sectionService.AjouterSection(s);
 		return "redirect:/sites/Index";
 	}
    
//    @RequestMapping("/FormUpdateRegion")
//	public String formUpdateRegion(Model modele,@RequestParam(name = "idRegion") int id) {
//    	Region r = sectionService.UneRegion(id);
//    	modele.addAttribute("region",r);
//		return "formulaireUpdateRegion";
//	}
//    
//    
//    @RequestMapping("/DeleteRegion")
//	public String DeleteRegion(@RequestParam(name = "idRegion") int id) {
//    	sectionService.SupprimerRegion(id);
//		return "redirect:/sites/Index";
//	}
//    
//    


//    
//    @RequestMapping("/UpdateRegion")
// 	public String UpdateRegion(@Valid Region r,Model modele, Errors errors) {
//    	if(errors.hasErrors()) {
//    		return "formulaireUpdateRegion";
//    	}
//    	sectionService.AjouterRegion(r);
// 		return "redirect:/sites/Index";
// 	}
//    
//    @RequestMapping("/details")
//	public String details(Model modele, @RequestParam(name = "idRegion") int id) {
//		Region r = sectionService.UneRegion(id);
//    	modele.addAttribute("r",r);
//		return "DetailsRegion";
//	}
}
