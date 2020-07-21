package ng.whycode.quotegenerator.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ng.whycode.quotegenerator.entities.Tag;

public interface ITagRepository extends CrudRepository<Tag,Long>{
	@Override
	public List<Tag> findAll();
	
	public Tag findByTag(String Tag);
}
