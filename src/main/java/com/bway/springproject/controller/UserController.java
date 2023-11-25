package com.bway.springproject.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bway.springproject.model.Slider;
import com.bway.springproject.service.SliderService;



@Controller
@RequestMapping("/User")
public class UserController {

	
	@Autowired
	private SliderService sliderservice;
	
	
	@GetMapping("/Logout")
	public String logout(HttpSession session) {
		
		session.invalidate();

		return "LoginForm";
	}
	
	@GetMapping("/Index")
	public String index(Model model) {

	    List<Slider> sliders = sliderservice.getAllSlis();
	    model.addAttribute("sliders", sliders);
		return "Index";
	}
	
}

