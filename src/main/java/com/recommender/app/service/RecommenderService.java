package com.recommender.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recommender.app.dao.ProductDao;
import com.recommender.app.dao.UserDao;
import com.recommender.app.model.Product;
import com.recommender.app.model.RecommenderUser;
import com.recommender.app.model.forms.QuestionaryForm;
import com.recommender.app.recommenders.BestRatingRecommender;
import com.recommender.app.recommenders.CollaborativeRecommender;
import com.recommender.app.recommenders.KnowledgeBasedRecommender;
import com.recommender.app.recommenders.Recommender;

@Service
public class RecommenderService {
		ProductDao productDao;
		UserDao userDao;
		Recommender recommender;
		
		@Autowired
		public RecommenderService(ProductDao productDao,UserDao userDao) {
			this.productDao = productDao;
			this.userDao = userDao;
		}
		public List<Product> getByCategory(String category) {
			List<Product> products = new ArrayList<Product>();
			for (Product prod : productDao.findAll()) {
				if (prod.getCategory().equals(category))
					products.add(prod);
			}
			return products;
		}
		private List<RecommenderUser> getSimilarUsers(int age,List<String> hobbies,String language) {
				List<RecommenderUser> output = new ArrayList<RecommenderUser>();
				for(RecommenderUser user:userDao.findAll()) {
					if(isSimilar(user,age,hobbies,language)) {
						output.add(user);
					}
				}
				return output;
		}
		private boolean isSimilar(RecommenderUser user, int age, List<String> hobbies, String language) {
			boolean similarAge = Math.abs(age-user.getQuestionary().getAgeUser())<=5;
			boolean similarHobbies = similarHobbie(QuestionaryForm.getCategories(user.getQuestionary().getHobbies()),hobbies);
			return similarAge&&similarHobbies;	
		}
		private boolean similarHobbie(List<String> hobbies1, List<String> hobbies2) {
			boolean found = false;
			int i = 0;
			while(!found&&i<hobbies1.size()) {
				if(hobbies2.contains(hobbies1.get(i)))
					found = true;
				++i;
			}
			return found;
		}
		private List<Product> getAllProducts() {
			List<Product> products = new ArrayList<Product>();
			Iterator<Product> iterator = productDao.findAll().iterator();
			while (iterator.hasNext())
				products.add(iterator.next());

			return products;
		}
		public List<Product> recommend(int age, int lower, int upper, List<String> hobbiesCategories, String language,boolean isGuest) {
			List<Product> recommended = new ArrayList<Product>();
			if(!isGuest) {
				recommender = new CollaborativeRecommender(age,lower,upper,getSimilarUsers(age,hobbiesCategories,language));
				recommended = recommender.recommend(getAllProducts());
				recommender = new KnowledgeBasedRecommender(age,lower,upper,hobbiesCategories);
				recommended = recommender.recommend(recommended);
			}
			else {
				recommender = new KnowledgeBasedRecommender(age,lower,upper,hobbiesCategories);
				recommended = recommender.recommend(getAllProducts());
				recommender = new BestRatingRecommender();
				recommended = recommender.recommend(recommended);
			}
	
			return recommended;
		}
}
