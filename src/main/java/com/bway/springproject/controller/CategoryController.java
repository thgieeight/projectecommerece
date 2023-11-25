package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Category;
import com.bway.springproject.service.CategoryService;


@Controller
@RequestMapping("/Admin/Category")
public class CategoryController {


	@Autowired
	private CategoryService catService;
	

	@GetMapping("/add")
	public String getCategory() {
		return "Admin/CategoryForm";
	}

	@PostMapping("/add")
	public String postCategory(@ModelAttribute Category cat) {
		catService.addCat(cat);
		return "/Admin/CategoryForm";
	}
	
	@GetMapping("/list")
	public String getlist(Model model) {
		model.addAttribute("catList", catService.getAllCats());
		return "/Admin/CategoryList";
	}
	

	@GetMapping("/delete")
	public String delete(@RequestParam int id) {
		
		catService.deleteCat(id);
		
		return "redirect:/Admin/CategoryList";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int id, Model model) {
		
		model.addAttribute("CatObject",catService.getCatById(id));
		
		return "/Admin/CategoryEdit";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Category cat) {
		
		catService.updateCat(cat);
		
		return "redirect:/Admin/Category/list";
	}

	
}
