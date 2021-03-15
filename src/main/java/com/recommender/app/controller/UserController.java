package com.recommender.app.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.recommender.app.controller.params.DashboardDTO;
import com.recommender.app.model.Questionary;
import com.recommender.app.model.RecommenderUser;
import com.recommender.app.model.forms.SignUpForm;
//import com.recommender.app.service.UserService;
import com.recommender.app.service.RecommenderUserService;

@RestController
@RequestMapping("/")
public class UserController {
	@Autowired
	RecommenderUserService service;
	@GetMapping("")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	@GetMapping("/login")
	public ModelAndView loginPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("signin");
		return modelAndView;
	}
	@GetMapping("/dashboard/users/search")
	public ModelAndView dashboardSearch(@RequestParam(required = true)String searchedUserName,Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");

		modelAndView.addObject("dashboardParams", service.getDashboardParams(principal.getName(), searchedUserName, true, true));
		return modelAndView;
	}
	@PostMapping("/dashboard/users/search")
	public ModelAndView dashboardFavorite(Principal principal,DashboardDTO params) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/dashboard");
		service.addFavorite(principal.getName(),params.getFoundUserName());
		return modelAndView;
	}
	@PostMapping("/dashboard/delete/favorite/{favorite}")
	public ModelAndView dashboardDeleteFavorite(@PathVariable(required = true)String favorite,Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/dashboard");
		service.deleteFavorite(principal.getName(), favorite);
		return modelAndView;
	}

	@GetMapping("/dashboard")
	public ModelAndView dashboard(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");
		modelAndView.addObject("dashboardParams", service.getDashboardParams(principal.getName(),"", false, true));
		return modelAndView;
	}
	@PostMapping("/dashboard")
	public ModelAndView updateQuestionary(Principal principal,@ModelAttribute DashboardDTO params) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");
		RecommenderUser currentUser = service.getByName(principal.getName());
		modelAndView.addObject("dashboardParams", service.getDashboardParams(currentUser.getUsername(), "", false, service.updateQuestionary(service.convertToForm(params.getCurrentUserQuestionary()), currentUser.getQuestionary().getId())));
		return modelAndView;
	}
	@PostMapping("signup")
	public ModelAndView signUp(@ModelAttribute SignUpForm form) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("signup");
		if(form.isValid()) {
			RecommenderUser user = new RecommenderUser();
			Questionary questionary = new Questionary();
			user.setUsername(form.getUsername());
			user.setPassword(form.getPassword1());
			user.setRole("USER");
			user.setDateCreated(new Date());
			user.setDateModified(new Date());
			questionary.setAgeUser(form.getAgeUser());
			questionary.setHobbies(form.getHobbies());
			questionary.setLanguage(form.getLanguage());
			questionary.setLowerBound(form.getLowerBound());
			questionary.setUpperBound(form.getUpperBound());
			if(service.addUser(user, questionary))
				modelAndView.setViewName("redirect:/");
			else {
				modelAndView.addObject("signUpForm", new SignUpForm());
				modelAndView.addObject("success", false);
			}
		}
		else {
			modelAndView.addObject("signUpForm", new SignUpForm());
			modelAndView.addObject("success", false);
		}
		return modelAndView;
	}
	@GetMapping("signup")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("signup");
		modelAndView.addObject("signUpForm", new SignUpForm());
		modelAndView.addObject("success", true);
		return modelAndView;

	}
}
