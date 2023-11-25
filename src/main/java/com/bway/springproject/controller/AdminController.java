package com.bway.springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bway.springproject.model.Brands;
import com.bway.springproject.model.Category;
import com.bway.springproject.model.Product;
import com.bway.springproject.model.Sell;
import com.bway.springproject.service.BrandsService;
import com.bway.springproject.service.CategoryService;
import com.bway.springproject.service.ProductService;
import com.bway.springproject.service.SellService;


@Controller
@RequestMapping("/Admin")
public class AdminController {

	
	@Autowired
	private BrandsService brandsService;
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private ProductService proService;
	


	@GetMapping("/Index")
	public String gethome(Model model) {   
	List<Category> catList = catService.getAllCats();
    List<Brands> brandsList = brandsService.getAllBrands();
    List<Product> productList = proService.getAllPros();

    

    model.addAttribute("catList", catList);
    model.addAttribute("brandsList", brandsList);
    model.addAttribute("productList", productList);


    // Add the counts to the model
    model.addAttribute("catListCount", catList.size());
    model.addAttribute("brandsListCount", brandsList.size());
    model.addAttribute("productListCount", productList.size());

		return "/Admin/Index";
	}
	
	
	@GetMapping("/LockScreen")
	public String getlock() {
		return "/Admin/PageLock";
	}


}
