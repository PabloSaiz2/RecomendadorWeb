package com.recommender.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.recommender.app.controller.params.DashboardDTO;
import com.recommender.app.model.Product;
import com.recommender.app.model.Questionary;
import com.recommender.app.model.forms.QuestionaryForm;
import com.recommender.app.recommenders.KnowledgeBasedRecommender;
import com.recommender.app.recommenders.Recommender;
import com.recommender.app.service.ProductService;
import com.recommender.app.service.RecommenderService;
import com.recommender.app.service.RecommenderUserService;

@RestController
@RequestMapping("/recommend")
public class RecommenderController {
	private RecommenderService service;
	@Autowired
	public RecommenderController(RecommenderService service) {
		this.service = service;
	}
	@GetMapping("")
	public ModelAndView recommendationGuest(@ModelAttribute QuestionaryForm questionary) {
		ModelAndView modelAndView = new ModelAndView("recommendation");
		modelAndView.addObject("age", questionary.getAgeUser());
		modelAndView.addObject("category", questionary.getHobbies());
		List<Product> recommended = new ArrayList<Product>();
		boolean success = true;
		recommended = service.recommend(questionary.getAgeUser(), 
				questionary.getLowerBound(), questionary.getUpperBound(), questionary.getHobbiesCategories(),
				questionary.getLanguage(), true);
		if(recommended.size()==0)
			success = false;
		modelAndView.addObject("success", success);
		modelAndView.addObject("products", recommended);
		return modelAndView;
	}
	@GetMapping("/{searchedUser}")
	public ModelAndView recommendationUser(@ModelAttribute DashboardDTO params) {
		ModelAndView modelAndView = new ModelAndView("recommendation");
		Questionary questionary = params.getSearchedQuestionary();
		modelAndView.addObject("age", questionary.getAgeUser());
		modelAndView.addObject("category", questionary.getHobbies());
		boolean success = true;
		List<Product> recommended = service.recommend(questionary.getAgeUser(),
				questionary.getLowerBound(), questionary.getUpperBound(),
				QuestionaryForm.getCategories(questionary.getHobbies()), 
				questionary.getLanguage(), false);
		if(recommended.size()==0)
			success = false;
		modelAndView.addObject("success", success);
		modelAndView.addObject("products", recommended);
		return modelAndView;
	}
	@GetMapping("/favorites/{favoriteUser}")
	public ModelAndView recommendationFavorite(@RequestParam(required = true, name = "age")int age,
			@RequestParam(required = true, name = "hobbies")String hobbies,
			@RequestParam(required = true, name = "language")String language,
			@RequestParam(required = true, name = "lower")int lower,
			@RequestParam(required = true, name = "upper")int upper) {
		ModelAndView modelAndView = new ModelAndView("recommendation");
		modelAndView.addObject("age", age);
		modelAndView.addObject("category",hobbies);
		boolean success = true;
		List<Product> recommended = service.recommend(age,lower,upper,QuestionaryForm.getCategories(hobbies),language,false);
		if(recommended.size()==0)
			success = false;
		modelAndView.addObject("success", success);
		modelAndView.addObject("products", recommended);
		return modelAndView;
	}
}
