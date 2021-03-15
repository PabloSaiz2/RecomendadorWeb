package com.recommender.app.model.forms;


public class ReviewForm {

	private int rating;
	private String text;	


	public ReviewForm() {
		this.text = "";
		this.rating = 0;  // solo valido si >= 0
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public boolean isValid() {
		return  !text.equals("") && rating >=0 && rating <=5;
	}

}
