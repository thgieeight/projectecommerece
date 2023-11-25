package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bway.springproject.service.SellService;

@Controller
@RequestMapping("/Admin/Sell")
public class SellController {

	@Autowired
	private SellService sellService;
	
	@GetMapping("/list")
	public String getlist(Model model) {
		model.addAttribute("sellList", sellService.getAllSell());
		return "/Admin/SellList";
	}
}
