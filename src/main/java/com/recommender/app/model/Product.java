package com.recommender.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  Long id;
	@Column(name = "name")
	private  String name;
	@Column(name="category")
	private  String category;
	@Column(name = "language")
	private  String language;
	@Column(name = "price")
	private int price;
	@Column(name = "rating")
	private int rating;
	@Column(name = "age")
	private int age;
	@OneToMany(mappedBy = "reviewed_product")
	private List<Review> reviews;
	@OneToMany(mappedBy = "product")
	List<ProductDetail> details;

	public Product() {
		
	}
	public Product(String name,String category,String language,int price,int rating,int age) {
		this.language = language;
		this.price = price;
		this.category = category;
		this.rating = rating;
		this.age = age;
		this.name = name;
	}
	
	public List<ProductDetail> getDetails() {
		return details;
	}
	public void setDetails(List<ProductDetail> details) {
		this.details = details;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age =age;
	}
	public String getName() {
		return name;
	}
	public String getCategory() {
		return category;
	}
	public String getLanguage() {
		return language;
	}
	public void updateRating() {
		int accumulatedRating = 0;
		for(Review review:reviews) {
			accumulatedRating+=review.getRating();
		}
		rating = accumulatedRating/reviews.size();
	}
	public void addReview(Review rev) {
		// TODO Auto-generated method stub
		reviews.add(rev);
	}
	
}
