package com.recommender.app.factories;


import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.recommender.app.model.Product;

@Component("productBuilder")
public class ProductBuilder extends Builder<Product> {
	public ProductBuilder() {
		super("Products");
	}

	@Override
	protected Product createTheInstance(JSONObject product) {
		
		return new Product(product.getString("name"), product.getString("category"),product.getString("language"),product.getInt("price"), product.getInt("rating"), product.getInt("age"));

	}
}
