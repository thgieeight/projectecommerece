package com.bway.springproject.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Product;
import com.bway.springproject.service.CategoryService;
import com.bway.springproject.service.ProductService;
@Controller
public class DetailsController {

    @Autowired
    private ProductService proService;

    @Autowired
    private CategoryService catService;

    @GetMapping("/Details")
    public String getLogin(HttpSession session, Model model, @RequestParam(name = "product", required = false) Integer id) {

        if (session.getAttribute("validuser") == null) {
            return "LoginForm";
        }

        if (id == null) {
            // If ID is null, fetch all products
            List<Product> productList = proService.getAllPros();
            model.addAttribute("productList", productList);
        } else {
            // If ID is provided, fetch the specific product based on the ID
            Product product = proService.getProdById(id);
            model.addAttribute("productList", product);

        }

        model.addAttribute("catList", catService.getAllCats());
        model.addAttribute("selectedid", id);

        return "Details";
    }
}

	
