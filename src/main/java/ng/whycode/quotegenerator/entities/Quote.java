package ng.whycode.quotegenerator.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	
	private String quoteName;
	
	@Column(columnDefinition = "varchar(255) default 'Unknown'",insertable=false)
	private String author;
	
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
	

	public Quote(String quoteName, String author, List<Tag> tags, Date createdAt, Date updatedAt) {
		super();
		this.quoteName = quoteName;
		this.author = author;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.tags = tags;
	}



	public String getQuoteName() {
		return quoteName;
	}


	public void setQuoteName(String quoteName) {
		this.quoteName = quoteName;
	}


	public long getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(long quoteId) {
		this.quoteId = quoteId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		
			this.author = author;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = new Date();
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = new Date();
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
}
