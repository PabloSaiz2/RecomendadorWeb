package com.recommender.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.recommender.app.model.forms.ReviewForm;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  Long id;
	
	@Column(name = "rating")
	private int rating;
	
	@Column(name = "message")
	private String message;
		
	@ManyToOne
	@JoinColumn(name = "reviewerId")
	private RecommenderUser reviewer ;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product reviewed_product;
	
	public Review() {}
	
	public Review(ReviewForm form) {
		rating = form.getRating();
		message = form.getText();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public RecommenderUser getReviewer() {
		return reviewer;
	}

	public void setReviewer(RecommenderUser reviewer) {
		this.reviewer = reviewer;
	}

	public Product getReviewed_product() {
		return reviewed_product;
	}

	public void setReviewed_product(Product reviewed_product) {
		this.reviewed_product = reviewed_product;
	}
	
	
	
}
