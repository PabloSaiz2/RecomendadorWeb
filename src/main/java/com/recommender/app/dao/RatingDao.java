package com.recommender.app.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.recommender.app.model.Review;

public interface RatingDao extends CrudRepository<Review,Long>{

	@Query(value = "SELECT * FROM review WHERE product_id = :productID",nativeQuery = true)
	Collection<Review> findAllByProduct(@Param("productID")Long product_id);
	@Query(value = "SELECT * FROM review WHERE reviewer_id = :userid",nativeQuery = true)
	Collection<Review> findAllByUser(@Param("userid")Long userid);
}
