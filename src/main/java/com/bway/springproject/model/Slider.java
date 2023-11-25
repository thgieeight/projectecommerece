package com.bway.springproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="slider_tbl")
public class Slider { 
	
	@Id
	@GeneratedValue
	private int id;
	private String heading;
	private String subheading;
	private String photo;
	private String status;

}
