package com.bway.springproject.service;

import java.util.List;

import com.bway.springproject.model.Brands;

public interface BrandsService {
	
	void addBrands(Brands brands);

	void deleteBrands(int id);

	void updateBrands(Brands brands);

	Brands getBrandsById(int id);

	List<Brands> getAllBrands();

}
