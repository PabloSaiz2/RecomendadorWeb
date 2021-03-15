package com.recommender.app.recommenders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.recommender.app.model.Product;

public class KnowledgeBasedRecommender implements Recommender {

	private int age;
	private int lowerBound;
	private int upperBound;
	private List<String> hobbies;
	public KnowledgeBasedRecommender(int age,int lowerBound,int upperBound,List<String> hobbies) {
		this.age = age;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.hobbies = hobbies;
	}
	@Override
	public List<Product> recommend(List<Product> products) {
		List<Product> candidates = new ArrayList<Product>();
		for(Product prod:products) {
			if(hobbies.contains(prod.getCategory())&&prod.getAge()<=age&&lowerBound<=prod.getPrice()&&upperBound>=prod.getPrice())
				candidates.add(prod);
		}
		return candidates;
	}
	

	
}
