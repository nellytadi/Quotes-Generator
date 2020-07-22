package ng.whycode.quotegenerator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/import/excel")
	public String viewImportExcel() {
		
		return "admin/quotes/import";
		
	}
	
	@PostMapping("/import/excel")
	public String importExcel(@RequestParam("file") MultipartFile files) throws IOException {
        


        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Quote quote = new Quote();

                XSSFRow row = worksheet.getRow(index);
            
               
                quote.setQuote(row.getCell(0).getStringCellValue());
                quote.setAuthor(row.getCell(1).getStringCellValue());
                quote.setCreatedAt(new Date());
                quote.setUpdatedAt(new Date());
                
                List<Tag> listtags = new ArrayList<Tag> ();
                
                Tag theTag = new Tag();
    			
    			theTag.setTag(row.getCell(2).getStringCellValue());
    			theTag.setCreatedAt(new Date());
    			theTag.setUpdatedAt(new Date());
    			Tag found = tagRepo.findByTag(row.getCell(2).getStringCellValue()) ;
    			
    			if(found == null) {
    				 tagRepo.save(theTag);
    			}
    			
    			listtags.add(found);
    			
    			quote.setTags(listtags);;
               
          
                quoteRepo.save(quote);
            }
        }

        return "redirect:/quote/create";
    }
}
