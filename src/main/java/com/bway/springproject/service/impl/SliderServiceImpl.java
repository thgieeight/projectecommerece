package com.bway.springproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Slider;
import com.bway.springproject.repository.SliderRepository;
import com.bway.springproject.service.SliderService;

@Service
public class SliderServiceImpl implements SliderService {

	@Autowired
	private SliderRepository sliderRepo;
	
	
	@Override
	public void addSlider(Slider sli) {
		sliderRepo.save(sli);
	}

	@Override
	public void deleteSlider(int id) {
		sliderRepo.deleteById(id);
	}

	@Override
	public void updateSlider(Slider sli) {
		sliderRepo.save(sli);
		
	}

	@Override
	public Slider getSliById(int id) {
		return sliderRepo.findById(id).get();
	}

	@Override
	public List<Slider> getAllSlis() {
		return sliderRepo.findAll();
	}

}
