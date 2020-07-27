package ng.whycode.quotegenerator.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ng.whycode.quotegenerator.dao.ITagRepository;
import ng.whycode.quotegenerator.entities.Tag;

public class TagBinder {

	
	@Autowired 
	ITagRepository tagRepo;
	
	
	public List<Tag> convertStringToListOfTagsAndSaveNewTags(String [] tagsFromForm){
		
		List<Tag> listtags = new ArrayList<Tag> ();
		
		for(String tag : tagsFromForm) {
			
			Tag theTag = new Tag();
					
			theTag.setTag(tag);
			theTag.setCreatedAt(new Date());
			theTag.setUpdatedAt(new Date());
					
					
			if(tagRepo.findByTag(tag) == null) {
				 tagRepo.save(theTag);
			}
					
			listtags.add(tagRepo.findByTag(tag));
		
		}
		
		return listtags;
		
	} 
	
	

}
