package com.recommender.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.recommender.app.dao.DetailDao;
import com.recommender.app.dao.ProductDao;
import com.recommender.app.dao.RatingDao;
import com.recommender.app.factories.Builder;
import com.recommender.app.model.Product;
import com.recommender.app.model.ProductDetail;

@Service
public class ProductService {
	private final ProductDao productDao;
	private final Builder<Product> builder;
	private final Builder<ProductDetail> builderDetail;
	private final DetailDao detailDao;
	private final RatingDao ratingDao;
	@Autowired
	public ProductService(ProductDao productDao,DetailDao detailDao,RatingDao ratingDao, 
			@Qualifier("productBuilder") Builder<Product> productBuilder,
			@Qualifier("detailBuilder") Builder<ProductDetail> detailBuilder) {
		this.builder = productBuilder;
		this.detailDao = detailDao;
		this.ratingDao = ratingDao;
		this.builderDetail = detailBuilder;
		this.productDao = productDao;
		//loadInDatabase()
	}

	private void loadInDatabaseProducts() {
		InputStream input = null;

		try {
			input = new FileInputStream(new File("src/main/resources/static/productsOut.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find json file");
		}

		if (input != null) {
			JSONObject jo = new JSONObject(new JSONTokener(input));
			JSONArray products = jo.getJSONArray("data");
			for (int i = 0; i < products.length(); ++i) {
				Product prod=productDao.save(builder.createInstance(jo, i));
				//List<ProductDetail> details = new ArrayList<ProductDetail>();
				for(int j = 0;j<products.getJSONObject(i).getJSONArray("data").length();++j) {
					ProductDetail detail = builderDetail.createInstance(products.getJSONObject(i), j);
					if(detail!= null) {
					detail.setProduct(prod);
					detail = detailDao.save(detail);
					//details.add(detail);
					}
				}
				//prod.setDetails(details);
				productDao.save(prod);
			}
		}

	}
	
	
	public boolean loadInDatabase() {
		
		ratingDao.deleteAll();
		detailDao.deleteAll();
		productDao.deleteAll();
		loadInDatabaseProducts();
		
		return true;
	}

	public List<Product> getByCategory(String category) {
		List<Product> products = new ArrayList<Product>();
		for (Product prod : productDao.findAll()) {
			if (prod.getCategory().equals(category))
				products.add(prod);
		}
		return products;
	}

	public List<Product> getAll() {
		List<Product> products = new ArrayList<Product>();
		Iterator<Product> iterator = productDao.findAll().iterator();
		while (iterator.hasNext())
			products.add(iterator.next());

		return products;
	}
}
