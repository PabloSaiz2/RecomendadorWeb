package com.recommender.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recommender.app.dao.ProductDao;
import com.recommender.app.dao.RatingDao;
import com.recommender.app.dao.UserDao;
import com.recommender.app.model.Product;
import com.recommender.app.model.RecommenderUser;
import com.recommender.app.model.Review;

@Service
public class ReviewService {

	RatingDao ratingDao;
	UserDao userDao;
	ProductDao productDao;

	@Autowired
	public ReviewService(RatingDao ratingDao, UserDao userDao, ProductDao productDao) {
		this.ratingDao = ratingDao;
		this.userDao = userDao;
		this.productDao = productDao;
	}
	public void poblateDatabaseWithReviews() {
		Random rand = new Random();
		int i = 0;
		for(RecommenderUser user:userDao.findAll()) {
			for(Product prod:productDao.findAll()) {
				Review currentReview = new Review();
				
				currentReview.setRating(rand.nextInt(6));
				currentReview.setMessage("Test rating");
				currentReview.setReviewer(user);
				currentReview.setReviewed_product(prod);
				currentReview = ratingDao.save(currentReview);
				if(i>0)
				prod.addReview(currentReview);
				prod.updateRating();
				productDao.save(prod);
				if(i>0)
				user.add_Review(currentReview);
				userDao.save(user);
			}
			++i;
		}
	}
	public void addReview(Review rev, String username, String productName) {
		if (!ratedProduct(rev, username, productName)) {
			RecommenderUser user = userDao.findByUsername(username);
			rev.setReviewer(user);
			Product p = productDao.findByName(productName);
			rev.setReviewed_product(p);
			rev = ratingDao.save(rev);
			p.addReview(rev);
			p.updateRating();
			productDao.save(p);
			user.add_Review(rev);
			userDao.save(user);
		}
	}

	private boolean ratedProduct(Review rev, String username, String productName) {
		RecommenderUser user = userDao.findByUsername(username);
		List<Review> reviews = user.getReviews();
		boolean rated = false;
		int i = 0;
		while(!rated&&i<reviews.size()) {
			if(productName.equals(reviews.get(i).getReviewed_product().getName()))
				rated = true;
			++i;
		}
		return rated;
	}
	public List<Review> getByProduct(Long productId){
		Collection<Review> reviews = ratingDao.findAllByProduct(productId);
		List<Review> filteredReviews = new ArrayList<Review>();
		Iterator<Review> iterator = reviews.iterator();
		while(iterator.hasNext()) {
			filteredReviews.add(iterator.next());
		}
		return filteredReviews;
	}
	public List<Review> getAll() {
		List<Review> reviews = new ArrayList<Review>();
		Iterator<Review> iterator = ratingDao.findAll().iterator();
		while(iterator.hasNext()) {
			reviews.add(iterator.next());
		}
		return reviews;
	}
	public List<Review> getByUser(String username) {
		// TODO Auto-generated method stub
		Collection<Review> reviews = ratingDao.findAllByUser(userDao.findByUsername(username).getId());
		List<Review> filteredReviews = new ArrayList<Review>();
		Iterator<Review> iterator = reviews.iterator();
		while(iterator.hasNext()) {
			filteredReviews.add(iterator.next());
		}
		return filteredReviews;
	}

}
