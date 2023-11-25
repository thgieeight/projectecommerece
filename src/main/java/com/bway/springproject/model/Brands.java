package com.bway.springproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="brands_tbl")
public class Brands {
	@Id
	@GeneratedValue
	private int id;
	private String bname;
	private String category;

}
