package com.bway.springproject.service;

import java.util.List;

import com.bway.springproject.model.Slider;


public interface SliderService {

	
	void addSlider(Slider sli);

	void deleteSlider(int id);

	void updateSlider(Slider sli);

	Slider getSliById(int id);

	List<Slider> getAllSlis();
}
