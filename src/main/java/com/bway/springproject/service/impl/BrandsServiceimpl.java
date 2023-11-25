package com.bway.springproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Brands;
import com.bway.springproject.repository.BrandsRepository;
import com.bway.springproject.service.BrandsService;
@Service
public class BrandsServiceimpl implements BrandsService   {

	@Autowired
	private BrandsRepository branRepo;
	
	@Override
	public void addBrands(Brands brands) {
			branRepo.save(brands);
	}

	@Override
	public void deleteBrands(int id) {
		branRepo.deleteById(id);
	}

	@Override
	public void updateBrands(Brands brands) {
		branRepo.save(brands);
		
	}

	@Override
	public Brands getBrandsById(int id) {
		return branRepo.findById(id).get();
	}

	@Override
	public List<Brands> getAllBrands() {
		return branRepo.findAll();
	}

}
