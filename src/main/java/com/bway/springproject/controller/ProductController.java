package com.bway.springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bway.springproject.model.Brands;
import com.bway.springproject.model.Category;
import com.bway.springproject.model.Product;
import com.bway.springproject.service.BrandsService;
import com.bway.springproject.service.CategoryService;
import com.bway.springproject.service.ProductService;
import com.bway.springproject.util.FileUtil;

@Controller
@RequestMapping("/Admin/Product")
public class ProductController {
	
	@Autowired
	private BrandsService brandsService;
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private ProductService proService;
	
	
	@Autowired
	private FileUtil fileUtil;
	
	@GetMapping("/add")
	private String getproduct(Model model) {
		   model.addAttribute("catlist", catService.getAllCats());
		    model.addAttribute("brandlist", brandsService.getAllBrands());
		    return "Admin/ProductForm";
	}
	
	@PostMapping("/add")
	public String addProduct(@ModelAttribute Product product, @RequestParam("image") MultipartFile image, Model model) {
	    if (!image.isEmpty()) {
	        fileUtil.fileUpload(image);
	        product.setPhoto(image.getOriginalFilename());
	        proService.addProduct(product);
	        model.addAttribute("message", "Upload success");
	    } else {
	        model.addAttribute("message", "Upload fail");
	    }

	    return "redirect:/Admin/Product/add"; // Redirect to the product add form
	}

		
	@GetMapping("/list")
	public String getlist(Model model) {
	    List<Category> catList = catService.getAllCats();
	    List<Brands> brandsList = brandsService.getAllBrands();
	    List<Product> productList = proService.getAllPros();

	    model.addAttribute("catList", catList);
	    model.addAttribute("brandsList", brandsList);
	    model.addAttribute("productList", productList);

	    return "/Admin/ProductList";
	}

	

	@GetMapping("/delete")
	public String delete(@RequestParam int id) {

	
		proService.deleteProduct(id);
		
		return "redirect:/Admin/Product/list";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int id, Model model) {

	    Product product = proService.getProById(id);
		model.addAttribute("catlist", catService.getAllCats());
		model.addAttribute("brandlist", brandsService.getAllBrands());
		model.addAttribute("ProdObject",proService.getProById(id));
	    model.addAttribute("existingPhoto", product.getPhoto());
		return "/Admin/ProductEdit";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Product product,@RequestParam("image") MultipartFile image) {
	    if (!image.isEmpty()) {
	        fileUtil.fileUpload(image);
	        product.setPhoto(image.getOriginalFilename());
	    } else {
	        Product existingProduct = proService.getProById(product.getId());
	        product.setPhoto(existingProduct.getPhoto()); // Use the existing photo if no new photo is selected
	    }
	    proService.updateProduct(product);
	    return "redirect:/Admin/Product/list";
	}

}
