package ng.whycode.quotegenerator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ng.whycode.quotegenerator.dao.IQuoteRepository;
import ng.whycode.quotegenerator.entities.Quote;

@Controller
@RequestMapping("/dashboard")
public class HomeController {

	@Autowired
	IQuoteRepository quoteRepo;
	
	
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
	public String tags() {
		return "admin/tags/tags";
	}
}
