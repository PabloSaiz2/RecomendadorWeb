package com.recommender.app.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.recommender.app.model.forms.QuestionaryForm;

@RestController
@RequestMapping("/questionary")
public class QuestionaryController {
	
	public QuestionaryController() {
		QuestionaryForm.initializeQuestionaries();
	}
	
	@GetMapping("")
	public ModelAndView questionary() {

		ModelAndView model = new ModelAndView();
		model.setViewName("questionary");
		model.addObject("questionary", new QuestionaryForm());
		model.addObject("success", true);
		
		return model;
	}
	
	@GetMapping("/invalid")
	public ModelAndView invalidQuestionary() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("questionary");
		modelAndView.addObject("success",false);
		modelAndView.addObject("questionary", new QuestionaryForm());

		return modelAndView;
	}
	
	
}
