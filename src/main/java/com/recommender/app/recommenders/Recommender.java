package com.recommender.app.recommenders;

import java.util.List;

import com.recommender.app.model.Product;

public interface Recommender {
	List<Product> recommend(List<Product> products);
}
