package com.bway.springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bway.springproject.model.Product;
import com.bway.springproject.service.BrandsService;
import com.bway.springproject.service.CategoryService;
import com.bway.springproject.service.ProductService;

@Controller
public class ShopController {


    @Autowired
    private ProductService proService;
    
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private BrandsService brandsService;
	
	@GetMapping("/Shop")
	public String getShop(Model model) {
        
        List<Product> productList = proService.getAllPros();
        model.addAttribute("productList", productList);
		model.addAttribute("catList", catService.getAllCats());
		model.addAttribute("BrandsList",brandsService.getAllBrands() );


		return "Shop";
	}
	
	
}
