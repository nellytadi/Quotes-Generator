package ng.whycode.quotegenerator.pojo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuoteForm {

	private long quoteId;
	
	@NotNull(message = "Please enter a quote")
	@Size(min=10)
	private String quoteName;
	
	private String author;
	@NotEmpty(message = "Please select or enter at least one tag")
	private String [] tags;
	

	public QuoteForm() {
		
	}
	

	public QuoteForm(String quoteName, String author, String [] tags) {
		super();
		this.quoteName = quoteName;
		this.author = author;
		this.tags = tags;
	}


	public long getQuoteId() {
		return quoteId;
	}


	public void setQuoteId(long quoteId) {
		this.quoteId = quoteId;
	}


	public String getQuoteName() {
		return quoteName;
	}


	public void setQuoteName(String quoteName) {
		this.quoteName = quoteName;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String[] getTags() {
		return tags;
	}


	public void setTags(String[] tags) {
		this.tags = tags;
	}



	
}
