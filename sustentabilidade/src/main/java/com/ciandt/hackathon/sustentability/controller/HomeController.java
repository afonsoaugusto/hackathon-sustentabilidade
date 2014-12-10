package com.ciandt.hackathon.sustentability.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = { "/home/", "/" })
public class HomeController {

	private static final Logger LOGGER = Logger.getLogger(HomeController.class);



	@RequestMapping(value = "/")
	public String index() {
		return "home/index";
	}


}
