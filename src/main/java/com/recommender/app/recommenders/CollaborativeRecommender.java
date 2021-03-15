package com.recommender.app.recommenders;

import java.util.ArrayList;
import java.util.List;

import com.recommender.app.model.Product;
import com.recommender.app.model.RecommenderUser;

public class CollaborativeRecommender implements Recommender{
	int age;
	int upperBound;
	int lowerBound;
	List<RecommenderUser> similarUsers;
	private static final int RATINGTHRESHOLD = 3;
	public CollaborativeRecommender(int age,int lowerBound,int upperBound,List<RecommenderUser> similar) {
		this.age =age;
		this.lowerBound=lowerBound;
		this.upperBound = upperBound;
		this.similarUsers = similar;
	}
	@Override
	public List<Product> recommend(List<Product> products) {
		List<Product> candidates = new ArrayList<Product>();
		for(Product prod:products) {
			if(validProduct(prod)) {
				int averageRating = getAverageRating(prod.getName());
				if(averageRating>=RATINGTHRESHOLD)
					candidates.add(prod);
			}
		}
		return candidates;
	}
	private int findProductRating(RecommenderUser user,String productName) {
		int pos =-1;
		int i=0;
		boolean found = false;
		while(!found&&i<user.getReviews().size()) {
			if(user.getReviews().get(i).getReviewed_product().getName().equals(productName)) {
				found = true;
				pos = i;
			}
			++i;
		}
		return pos;
	}
	private int getAverageRating(String productName) {
		int ratings = 0;
		int output = 0;
		int accumulated = 0;
		for(RecommenderUser user:similarUsers) {
			int posRating = findProductRating(user,productName);
			if(posRating!=-1) {
				++ratings;
				accumulated += user.getReviews().get(posRating).getRating();
			}
		}
		if(ratings>0)
			output = accumulated/ratings;
		return output;
	}
	private boolean validProduct(Product prod) {
		return prod.getAge()<=age&&prod.getPrice()>lowerBound&&prod.getPrice()<upperBound;
	}
}
