package com.recommender.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Questionary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "age")
	private int ageUser;
	@Column(name = "hobbies")
	private String hobbies;
	@Column(name = "language")
	private String language;
	@Column(name = "lower_bound")
	private int lowerBound;
	@Column(name = "upper_bound")
	private int upperBound;
	@OneToOne(mappedBy = "questionary")
	private RecommenderUser owner;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAgeUser() {
		return ageUser;
	}
	public void setAgeUser(int ageUser) {
		if(ageUser>0&&ageUser<150)
			this.ageUser = ageUser;
		
	}
	
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
			this.hobbies = hobbies;	
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
			this.language = language;
		
	}
	public int getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(int lowerBound) {
			this.lowerBound = lowerBound;
	}
	public int getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(int upperBound) {
			this.upperBound = upperBound;
	}
}
