package com.bway.springproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Product;
import com.bway.springproject.repository.ProductRepository;
import com.bway.springproject.service.ProductService;
@Service
public class ProductServiceimpl implements ProductService {

	@Autowired
	private ProductRepository proRepo;
	
	@Override
	public void addProduct(Product product) {
		proRepo.save(product);
	}

	@Override
	public void deleteProduct(int id) {
		proRepo.deleteById(id);;
	}

	@Override
	public void updateProduct(Product product) {
		proRepo.save(product);
		
	}

	@Override
	public Product getProById(int id) {
		return proRepo.findById(id).get();
	}

	@Override
	public List<Product> getAllPros() {
		return proRepo.findAll();
	}

	@Override
	public List<Product> getProductsByCategoryId(int categoryId) {
        return proRepo.findByCategory_Id(categoryId);

	}

	@Override
	public Product getProdById(Integer id) {
		return proRepo.findById(id).get();

	}


}
