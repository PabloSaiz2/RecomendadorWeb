package com.recommender.app.dao;


import org.springframework.data.repository.CrudRepository;
import com.recommender.app.model.Product;

public interface ProductDao extends CrudRepository<Product,Long>{
	Product findByName(String name);
}
