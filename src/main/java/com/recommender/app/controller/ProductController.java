package com.recommender.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.recommender.app.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductService service;
	@Autowired
	public ProductController(ProductService service) {
		this.service = service;
	}
	
	@GetMapping("")
	public ModelAndView showAll() {
		ModelAndView modelAndView = new ModelAndView("products");
		modelAndView.addObject("products", service.getAll());
		return modelAndView;
	}
	@GetMapping("/loadProducts")
	public String load() {
		return "Did load products to the database? "+this.service.loadInDatabase();
	}
	
}
