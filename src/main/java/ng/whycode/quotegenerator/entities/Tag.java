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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;


@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_generator")
	@SequenceGenerator(name = "tag_generator", sequenceName = "tag_seq", allocationSize = 1)
	private long tagId;
	
	

	@NotNull
	@Size(min=2)
	@Column(unique = true)
	private String tag;
	
	private Date createdAt;
	private Date updatedAt;
	@ManyToMany(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
			   fetch = FetchType.LAZY)
	@JoinTable(name="quote_tag",
	joinColumns=@JoinColumn(name="tag_id"),
	inverseJoinColumns= @JoinColumn(name="quote_id")
			)
	@JsonIgnore
	private List<Quote> quotes;
	
	public Tag() {
		
	}

	public Tag(String tag, Date createdAt,Date updatedAt) {
		super();
		this.tag = tag;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Tag(String tag, Date createdAt,Date updatedAt, List<Quote> quotes) {
		super();
		this.tag = tag;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.quotes = quotes;
	}
	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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
	
	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}
}
