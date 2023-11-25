package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Brands;
import com.bway.springproject.service.BrandsService;
import com.bway.springproject.service.CategoryService;

@Controller
@RequestMapping("/Admin/Brands")
public class BrandsController {
	
	@Autowired
	private BrandsService brandsService;
	
	@Autowired
	private CategoryService catService;
	
	@GetMapping("/add")
	private String getbrands(Model model) {
		
		model.addAttribute("catlist", catService.getAllCats());

		return "/Admin/BrandsForm";
	}
	
	@PostMapping("/add")
	private String postbrands(@ModelAttribute Brands brands) {
		brandsService.addBrands(brands);
		return "/Admin/BrandsForm";
	}

	@GetMapping("/list")
	public String getlist(Model model) {
		model.addAttribute("brandsList", brandsService.getAllBrands());
		return "/Admin/BrandsList";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam int id) {

	
		brandsService.deleteBrands(id);
		
		return "redirect:/Admin/BrandsList";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int id, Model model) {

		
		model.addAttribute("catlist", catService.getAllCats());

		model.addAttribute("BrandsObject",brandsService.getBrandsById(id));
		
		return "/Admin/BrandsEdit";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Brands brands) {
		
			
		brandsService.updateBrands(brands);
		
		return "redirect:/Admin/Brands/list";
	}
}
