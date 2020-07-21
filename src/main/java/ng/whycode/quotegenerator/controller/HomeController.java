package ng.whycode.quotegenerator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ng.whycode.quotegenerator.dao.IQuoteRepository;
import ng.whycode.quotegenerator.dao.ITagRepository;
import ng.whycode.quotegenerator.entities.Quote;
import ng.whycode.quotegenerator.entities.Tag;

@Controller
@RequestMapping("/dashboard")
public class HomeController {

	@Autowired
	IQuoteRepository quoteRepo;
	@Autowired 
	ITagRepository tagRepo;
	
	@GetMapping("/")
	public String view() {
		return "admin/index";
	}
	
	@GetMapping("/quotes")
	public String quotes(Model model) {
		List<Quote> quotes = quoteRepo.findAll();
		
		model.addAttribute("quotes",quotes);
		
		return "admin/quotes/quotes";
	}
	
	@GetMapping("/tags")
	public String tags(Model model,Tag tag) {
		
		model.addAttribute("tag",tag);
		
		List <Tag> tags = tagRepo.findAll();
		model.addAttribute("tags", tags);
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String jsonString = mapper.writeValueAsString(tags);
			model.addAttribute("jsontags", jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "admin/tags/tags";
	}
}
