package com.recommender.app.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.recommender.app.controller.params.DashboardDTO;
import com.recommender.app.converters.QuestionaryToFormConverter;
import com.recommender.app.dao.QuestionaryDao;
import com.recommender.app.dao.RatingDao;
import com.recommender.app.dao.UserDao;
import com.recommender.app.model.Questionary;
import com.recommender.app.model.RecommenderUser;
import com.recommender.app.model.forms.QuestionaryForm;

@Service
public class RecommenderUserService {
	@Autowired
	UserDao userDao;
	@Autowired
	RatingDao ratingDao;
	@Autowired
	QuestionaryDao questionaryDao;
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	QuestionaryToFormConverter converter;
	public boolean addUser(RecommenderUser user,Questionary questionary) {
		if(!usernameTaken(user.getUsername())) {
			Questionary userQuestionary = questionaryDao.save(questionary);
			user.setQuestionary(userQuestionary);
			user.setPassword(encoder.encode(user.getPassword()));
			userDao.save(user);
			return true;
		}
		return false;
	}
	private boolean usernameTaken(String username) {
		Iterator<RecommenderUser> iterator = userDao.findAll().iterator();
		boolean taken = false;
		while(iterator.hasNext()&&!taken){
			taken = iterator.next().getUsername().equals(username);
		}
		return taken;
	}
	
	public boolean updateQuestionary(QuestionaryForm questionaryForm,Long idQuestionary) {
		if(questionaryForm.isValid()) {
			Questionary questionary= questionaryDao.findById(idQuestionary).get();
			questionary.setHobbies(questionaryForm.getHobbies());
			questionary.setAgeUser(questionaryForm.getAgeUser());
			questionary.setLanguage(questionaryForm.getLanguage());
			questionary.setLowerBound(questionaryForm.getLowerBound());
			questionary.setUpperBound(questionaryForm.getUpperBound());
			questionaryDao.save(questionary);
			return true;
		}
		else
			return false;
	}
	public RecommenderUser getByName(String name) {
		return userDao.findByUsername(name);
	}
	public DashboardDTO getDashboardParams(String currentUser,String searchedUser,boolean searched,boolean updateSuccessfull) {
		DashboardDTO output = new DashboardDTO();
		RecommenderUser current = userDao.findByUsername(currentUser);
		output.setCurrentUserQuestionary(current.getQuestionary());
		output.setCurrentUserName(currentUser);
		output.setFavorites(current.getFavoriteList());
		output.setFoundUserName(searchedUser);
		output.setSuccessUpdate(updateSuccessfull);
		output.setSearched(searched);
		RecommenderUser searchedUserInstance = userDao.findByUsername(searchedUser);
		if(searchedUserInstance!=null) {
			output.setSuccessSearch(true);
			output.setSearchedIsFavorite(current.getFavoriteList().contains(searchedUserInstance));
			Questionary searchedQuestionary = searchedUserInstance.getQuestionary();
			output.setSearchedQuestionary(searchedQuestionary);
		}
		else if(!searchedUser.equals("")) {
			output.setSearchedQuestionary(new Questionary());
			output.setSuccessSearch(false);
			output.setSearchedIsFavorite(false);
		}
		else {
			output.setSearchedQuestionary(new Questionary());
			output.setSuccessSearch(true);
			output.setSearchedIsFavorite(false);
		}
		
		return output;
	}
	public QuestionaryForm convertToForm(Questionary source) {
		return converter.convert(source);
	}
	public void deleteFavorite(String currentUser, String favorite) {
		RecommenderUser current = userDao.findByUsername(currentUser);
		RecommenderUser favoriteUser = userDao.findByUsername(favorite);
		List<RecommenderUser> favorites = current.getFavoriteList();
		favorites.remove(favoriteUser);
		current.setFavoriteList(favorites);
		userDao.save(current);
	}
	public void addFavorite(String currentUser, String favorite) {
		// TODO Auto-generated method stub
		RecommenderUser current = userDao.findByUsername(currentUser);
		RecommenderUser favoriteUser = userDao.findByUsername(favorite);
		List<RecommenderUser> favorites = current.getFavoriteList();
		favorites.add(favoriteUser);
		current.setFavoriteList(favorites);
		userDao.save(current);
	}
}
