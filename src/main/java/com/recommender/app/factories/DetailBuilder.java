package com.recommender.app.factories;


import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.recommender.app.model.ProductDetail;

@Component("detailBuilder")
public class DetailBuilder extends Builder<ProductDetail>{

	public DetailBuilder() {
		super("Detail");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ProductDetail createTheInstance(JSONObject product) {
		ProductDetail detail = new ProductDetail();
		detail.setName(product.getString("name"));
		detail.setDetail(product.getString("detail"));
		return detail;
	}

	

	

}
