package com.bway.springproject.service;

import java.util.List;

import com.bway.springproject.model.Product;


public interface ProductService {

	void addProduct(Product product);

	void deleteProduct(int id);

	void updateProduct(Product product);

	Product getProById(int id);

	List<Product> getAllPros();
	
    List<Product> getProductsByCategoryId(int categoryId);

	Product getProdById(Integer id);

}
