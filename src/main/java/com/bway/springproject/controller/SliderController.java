package com.bway.springproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bway.springproject.model.Slider;
import com.bway.springproject.service.SliderService;
import com.bway.springproject.util.FileUtil;

@Controller
@RequestMapping("/Admin/Slider")
public class SliderController {

	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private SliderService sliderservice;
	
	
	@GetMapping("/add")
	public String getSlider() {
		return "Admin/SliderForm";
	}
	
	@PostMapping("/add")
	public String postslider(@ModelAttribute Slider slider,@RequestParam("image") MultipartFile image,Model model) {
	    if (!image.isEmpty()) {
	        fileUtil.fileUpload(image);
	        slider.setPhoto(image.getOriginalFilename());
	        sliderservice.addSlider(slider);
	        model.addAttribute("message", "Upload success");
	    } else {
	        model.addAttribute("message", "Upload fail");
	    }
	    model.addAttribute("slider", slider); 
	    return "/Admin/SliderForm";
	}
	
	
	@GetMapping("/list")
	public String getlist(Model model) {
		model.addAttribute("sliderlist", sliderservice.getAllSlis());

		return "/Admin/SliderList";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int id) {
		
		sliderservice.deleteSlider(id);
		
		return "redirect:/Admin/Slider/list";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int id, Model model) {
	    Slider slider = sliderservice.getSliById(id);
	    model.addAttribute("sliderObject", slider);
	    model.addAttribute("existingPhoto", slider.getPhoto());
	    return "/Admin/SliderEdit";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute Slider sli, @RequestParam("image") MultipartFile image) {
	    if (!image.isEmpty()) {
	        fileUtil.fileUpload(image);
	        sli.setPhoto(image.getOriginalFilename());
	    } else {
	        Slider existingSlider = sliderservice.getSliById(sli.getId());
	        sli.setPhoto(existingSlider.getPhoto()); // Use the existing photo if no new photo is selected
	    }
	    sliderservice.updateSlider(sli);
	    return "redirect:/Admin/Slider/list";
	}



	
}
