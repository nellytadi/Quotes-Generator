package ng.whycode.quotegenerator.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ng.whycode.quotegenerator.dao.IQuoteRepository;
import ng.whycode.quotegenerator.dao.ITagRepository;
import ng.whycode.quotegenerator.entities.Quote;
import ng.whycode.quotegenerator.entities.Tag;

@Controller
@RequestMapping("/quote")
public class QuoteController {
	@Autowired
	IQuoteRepository quoteRepo;
	
	@Autowired 
	ITagRepository tagRepo;
	
	@GetMapping("/create")
	public String create(Model model, Quote quote) {
		List <Tag> tags = tagRepo.findAll();
		model.addAttribute("tags", tags);
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String jsonString = mapper.writeValueAsString(tags);
			model.addAttribute("jsontags", jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("quote", quote);
		return "admin/quotes/create";
	}
	
	@PostMapping("/store")
	public String store(@RequestParam String quote,@RequestParam String author, @RequestParam String [] tags) {
		

		List<Tag> listtags = new ArrayList<Tag> ();
		
		for(String tag : tags) {
			
			Tag theTag = new Tag();
			
			theTag.setTag(tag);
			theTag.setCreatedAt(new Date());
			theTag.setUpdatedAt(new Date());
			Tag found = tagRepo.findByTag(tag) ;
			
			if(found == null) {
				 tagRepo.save(theTag);
			}
			
			listtags.add(found);

		}
	
		
		Quote quotes = new Quote();
		
		quotes.setQuote(quote);
		quotes.setAuthor(author);
		quotes.setCreatedAt(new Date());
		quotes.setUpdatedAt(new Date());
		quotes.setTags(listtags);
		
		quoteRepo.save(quotes);
		return "redirect:/dashboard/quotes";
	}

}
