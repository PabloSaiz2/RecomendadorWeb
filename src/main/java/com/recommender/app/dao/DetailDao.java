package com.recommender.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.recommender.app.model.ProductDetail;

public interface DetailDao extends CrudRepository<ProductDetail,Long>{

}
