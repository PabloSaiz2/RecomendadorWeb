package com.recommender.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class RecommenderUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "username" ,nullable = false,unique = true)
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;
	@OneToOne
	@JoinColumn(name = "questionary_id",referencedColumnName = "id",
	nullable = false,unique = true)
	private Questionary questionary;
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated = new Date(); 
	@Column(name = "modified")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified = new Date(); 
	@OneToMany(mappedBy = "reviewer")
	List<Review> reviews;
	@ManyToMany
	@JoinTable(name = "favorites", 
			  joinColumns = @JoinColumn(name = "user_id"), 
			  inverseJoinColumns = @JoinColumn(name = "favorite_id"))
	private List<RecommenderUser> favoriteList;
	
	public List<RecommenderUser> getFavoriteList() {
		return favoriteList;
	}
	public void setFavoriteList(List<RecommenderUser> favoriteList) {
		this.favoriteList = favoriteList;
	}

  public List<Review> getReviews() {
		return reviews;
	}
	
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void add_Review(Review review) {
		this.reviews.add(review);
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	public Questionary getQuestionary() {
		return questionary;
	}
	public void setQuestionary(Questionary questionary) {
		this.questionary = questionary;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
