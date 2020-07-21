package ng.whycode.quotegenerator.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ng.whycode.quotegenerator.entities.Quote;

public interface IQuoteRepository extends CrudRepository<Quote,Long>{
	@Override
	public List <Quote> findAll();
}
