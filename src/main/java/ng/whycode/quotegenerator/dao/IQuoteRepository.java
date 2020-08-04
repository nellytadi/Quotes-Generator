package ng.whycode.quotegenerator.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import ng.whycode.quotegenerator.entities.Quote;

public interface IQuoteRepository extends PagingAndSortingRepository<Quote,Long>{
	@Override
	public Page<Quote> findAll(Pageable pageable);
}
