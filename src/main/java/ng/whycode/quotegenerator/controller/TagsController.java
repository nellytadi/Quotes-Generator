package ng.whycode.quotegenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ng.whycode.quotegenerator.entities.Quote;

@Controller
@RequestMapping("/tag")
public class TagsController {

	@GetMapping("/create")
	public String create(Model model, Quote quote) {
		return "admin/tags/create";
	}

	
}
