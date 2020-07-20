package ng.whycode.quotegenerator.dao;

import org.springframework.data.repository.CrudRepository;

import ng.whycode.quotegenerator.entities.Tag;

public interface ITagRepository extends CrudRepository<Tag,Long>{

}
