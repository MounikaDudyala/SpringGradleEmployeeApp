package com.gradleemployeeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
	@RequestMapping(value="/")
	 private ModelAndView homePage()
	 {
		ModelAndView model = new ModelAndView("home");
		 return model;

	 }
}
