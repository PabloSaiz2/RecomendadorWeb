package com.recommender.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.recommender.app.model.Questionary;

public interface QuestionaryDao extends CrudRepository<Questionary,Long>{

}
