package com.recommender.app.recommenders;

import java.util.ArrayList;
import java.util.List;

import com.recommender.app.model.Product;

public class BestRatingRecommender implements Recommender {

	@Override
	public List<Product> recommend(List<Product> products) {
		Product best = null;
		int bestRating = 0;
		for(Product prod:products) {
			if(prod.getRating()>=bestRating) {
				best = prod;
				bestRating = prod.getRating();
			}
		}
		List<Product> output = new ArrayList<Product>();
		if(best !=null) {
			output.add(best);
		}
		return output;
	}

}
