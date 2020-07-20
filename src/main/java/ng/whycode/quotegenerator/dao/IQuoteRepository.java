package ng.whycode.quotegenerator.dao;

import org.springframework.data.repository.CrudRepository;

import ng.whycode.quotegenerator.entities.Quote;

public interface IQuoteRepository extends CrudRepository<Quote,Long>{

}
