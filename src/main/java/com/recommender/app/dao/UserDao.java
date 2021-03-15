package com.recommender.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.recommender.app.model.RecommenderUser;

public interface UserDao extends CrudRepository<RecommenderUser,String>{
	RecommenderUser findByUsername(String username);
}
