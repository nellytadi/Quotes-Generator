package ng.whycode.quotegenerator.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Quote {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quote_generator")
	@SequenceGenerator(name = "quote_generator", sequenceName = "quote_seq", allocationSize = 1)
	private long quoteId;

	private String quote;
	private String createdBy;
	private Date createdAt;
	private Date updatedAt;
	
	@ManyToMany(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
			   fetch = FetchType.LAZY)
	@JoinTable(name="quote_tag",
	joinColumns=@JoinColumn(name="quote_id"),
	inverseJoinColumns= @JoinColumn(name="tag_id")
			)
	@JsonIgnore
	private List<Tag> tags;
	
	public Quote() {
		
	}
	
	public Quote(String quote, String createdBy, Date createdAt) {
		super();
		
		this.quote = quote;
		this.createdBy = createdBy;
		this.createdAt = createdAt;

	}
	public Quote(String quote, String createdBy, List<Tag> tags) {
		super();
		this.quote = quote;
		this.createdBy = createdBy;
		this.tags = tags;
	}

	public long getQuoteId() {
		return quoteId;
	}


	public void setQuoteId(long quoteId) {
		this.quoteId = quoteId;
	}


	public String getQuote() {
		return quote;
	}


	public void setQuote(String quote) {
		this.quote = quote;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
