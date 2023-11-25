package com.bway.springproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Category;
import com.bway.springproject.repository.CategoryRepository;
import com.bway.springproject.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository catRepo;
	
	@Override
	public void addCat(Category cat) {
		catRepo.save(cat);
	}

	@Override
	public void deleteCat(int id) {
		catRepo.deleteById(id);
	}

	@Override
	public void updateCat(Category cat) {
		catRepo.save(cat);
		
	}

	@Override
	public Category getCatById(int id) {
		return catRepo.findById(id).get();
	}

	@Override
	public List<Category> getAllCats() {
		return catRepo.findAll();
	}

}
