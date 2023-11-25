package com.bway.springproject.service;

import java.util.List;


import com.bway.springproject.model.Category;

public interface CategoryService {
	
	void addCat(Category cat);

	void deleteCat(int id);

	void updateCat(Category cat);

	Category getCatById(int id);

	List<Category> getAllCats();
}
