package br.com.primeshoes.api.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "product_variation_id")
	private ProductVariation productVariation;
	
	private int rating;
	private String comment;
	private Date createdAt;
	
	public Review() {
	    
	}
	public Review(long id, User user, ProductVariation productVariation, int rating, String comment,
		Date createdAt) {
	    super();
	    this.id = id;
	    this.user = user;
	    this.productVariation = productVariation;
	    this.rating = rating;
	    this.comment = comment;
	    this.createdAt = createdAt;
	}
	public long getId() {
	    return id;
	}
	public void setId(long id) {
	    this.id = id;
	}
	public User getUser() {
	    return user;
	}
	public void setUser(User user) {
	    this.user = user;
	}
	public ProductVariation getProductVariation() {
	    return productVariation;
	}
	public void setProductVariation(ProductVariation productVariation) {
	    this.productVariation = productVariation;
	}
	public int getRating() {
	    return rating;
	}
	public void setRating(int rating) {
	    this.rating = rating;
	}
	public String getComment() {
	    return comment;
	}
	public void setComment(String comment) {
	    this.comment = comment;
	}
	public Date getCreatedAt() {
	    return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
	    this.createdAt = createdAt;
	}
	
	
}
