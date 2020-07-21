package ng.whycode.quotegenerator.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ng.whycode.quotegenerator.dao.ITagRepository;
import ng.whycode.quotegenerator.entities.Tag;

@Controller
@RequestMapping("/tag")
public class TagsController {

	@Autowired 
	ITagRepository tagRepo;
	
	
	
	
	@PostMapping("/store")
	public String store(Model model, @RequestParam String [] tag) {
		
		for(String t : tag) {
			
			Tag theTag = new Tag();
			
			theTag.setTag(t);
			theTag.setCreatedAt(new Date());
			theTag.setUpdatedAt(new Date());
			
			if(tagRepo.findByTag(t) == null) {
				 tagRepo.save(theTag);
			}
			
		}
		
		return "redirect:/dashboard/tags";
	}
	
}
