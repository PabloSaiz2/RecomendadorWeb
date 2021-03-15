package com.recommender.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.recommender.app.model.Review;
import com.recommender.app.model.forms.ReviewForm;
import com.recommender.app.service.ReviewService;


@RestController
@RequestMapping("/review")
public class ReviewController {

	private ReviewService service;

	@Autowired
	public ReviewController(ReviewService service) {
		this.service = service;
	}
	@GetMapping("/addReviews")
	public String addReviews() {
		service.poblateDatabaseWithReviews();
		return "Reviews added";
	}
	@GetMapping("/{productName}")
	public ModelAndView reviewDashboard(@PathVariable(required = true) String productName) {
		ModelAndView model = new ModelAndView();
		model.setViewName("reviews");
		model.addObject("productName", productName);
		model.addObject("reviewForm", new ReviewForm());
		return model;
	}

	@PostMapping("/{productName}")
	public ModelAndView AddReview(@PathVariable String productName, @ModelAttribute ReviewForm reviewForm,
			Principal ppal) {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/products");
		if (reviewForm.isValid()) {
			Review r = new Review(reviewForm);
			service.addReview(r, ppal.getName(), productName);
		}
		return model;
	}

	@GetMapping("/reviews")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView();
		List<Review> reviews = service.getAll();
		modelAndView.setViewName("reviewslist");
		modelAndView.addObject("reviews", reviews);
		return modelAndView;
	}
	@GetMapping("/reviews/{currentUserName}")
	public ModelAndView listarPorUsuario(@PathVariable String currentUserName) {
		ModelAndView modelAndView = new ModelAndView();
		List<Review> reviews = service.getByUser(currentUserName);
		modelAndView.setViewName("reviewslist");
		modelAndView.addObject("reviews", reviews);
		return modelAndView;
	}
	@GetMapping("/reviews/productId/{productId}")
	public ModelAndView listarPorProduct(@PathVariable(required = true)Long productId) {
		ModelAndView modelAndView = new ModelAndView();
		List<Review> reviews = service.getByProduct(productId);
		modelAndView.setViewName("reviewslist");
		modelAndView.addObject("reviews", reviews);
		return modelAndView;
	}

}
