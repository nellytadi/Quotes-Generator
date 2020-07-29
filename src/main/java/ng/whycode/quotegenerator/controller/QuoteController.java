package ng.whycode.quotegenerator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ng.whycode.quotegenerator.dao.IQuoteRepository;
import ng.whycode.quotegenerator.dao.ITagRepository;
import ng.whycode.quotegenerator.entities.Quote;
import ng.whycode.quotegenerator.entities.Tag;
import ng.whycode.quotegenerator.pojo.QuoteForm;

@Controller
@RequestMapping("/quote")
public class QuoteController {
	
	@Autowired
	IQuoteRepository quoteRepo;
	
	@Autowired 
	ITagRepository tagRepo;
	
	@GetMapping("/create")
	public String create(Model model, QuoteForm quote) {
		List <Tag> tags = tagRepo.findAll();
		
		model.addAttribute("tags", tags);
		
		model.addAttribute("jsontags", returnTagsAsJson(tagRepo.findAll()));
		
		model.addAttribute("quote", quote);
		
		return "admin/quotes/create";
	}
	
	@PostMapping("/store")
	public String store(@Valid QuoteForm quoteform,BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("jsontags", returnTagsAsJson(tagRepo.findAll()));
			return "admin/quotes/create";
		}
		
		List<Tag> listtags = firstOrCreateListTags(quoteform.getTags());
		
			Quote quote = new Quote();
			quote.setAuthor(quoteform.getAuthor());
			quote.setQuoteName(quoteform.getQuoteName());
			quote.setTags(listtags);
			quoteRepo.save(quote);
		
		return "redirect:/dashboard/quotes";
	}

	
	


	@GetMapping("/import/excel")
	public String viewImportExcel() {
		
		return "admin/quotes/import";
		
	}
	
	@PostMapping("/import/excel")
	public String importExcel(@RequestParam("file") MultipartFile files) throws IOException {
       

        try (XSSFWorkbook xssfWorkbook = new XSSFWorkbook(files.getInputStream())) {
			XSSFSheet worksheet = xssfWorkbook.getSheetAt(0);

			for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			    if (index > 0) {
			    	
			    	XSSFRow row = worksheet.getRow(index);
			    	
			    	String r_quote = row.getCell(0).getStringCellValue();
			    	String r_author = row.getCell(1).getStringCellValue();
			    	
			    	Quote quote = new Quote();
			       
			        quote.setQuoteName(r_quote);
			        
			        if(r_author == null) 
			        	quote.setAuthor("Anonymous");
			        else 
			        	quote.setAuthor(r_author);
			        
			        quote.setCreatedAt(new Date());
			        quote.setUpdatedAt(new Date());
			        
			        List<Tag> listtags = new ArrayList<Tag> ();
			        
			        Tag theTag = new Tag();
					
					theTag.setTag(row.getCell(2).getStringCellValue());
					theTag.setCreatedAt(new Date());
					theTag.setUpdatedAt(new Date());
					
					
					if(tagRepo.findByTag(row.getCell(2).getStringCellValue()) == null) {
						 tagRepo.save(theTag);
					}
					
					listtags.add(tagRepo.findByTag(row.getCell(2).getStringCellValue()));
					
					quote.setTags(listtags);
			       
			  
			        quoteRepo.save(quote);
			    }
			}
		}
		return "redirect:/quote/create";
    }
	
	@GetMapping("/update/{id}")
	public String update(Model model, @PathVariable long id) {
		
		List <Tag> tags = tagRepo.findAll();
		model.addAttribute("tags", tags);
		
		
		model.addAttribute("jsontags", returnTagsAsJson(tags));
		
		Quote quote = quoteRepo.findById(id).get();
		
		model.addAttribute("quote", quote);
	
		return "admin/quotes/create";
	
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		
		quoteRepo.deleteById(id);
		
		return "redirect:/dashboard/quotes";
	
	}	
	
	public String returnTagsAsJson(List <Tag> tags) {
		
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(tags);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
	private List<Tag> firstOrCreateListTags(String [] tags) {
		
		List <Tag> listTags = new ArrayList<Tag>();

		for(String tag : tags) {
			
			Tag theTag = new Tag();
					
			theTag.setTag(tag);
			theTag.setCreatedAt(new Date());
			theTag.setUpdatedAt(new Date());
					
					
			if(tagRepo.findByTag(tag) == null) {
				 tagRepo.save(theTag);
			}
					
			listTags.add(tagRepo.findByTag(tag));
		
		}
		return listTags;
	}
}
