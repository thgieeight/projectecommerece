package com.bway.springproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sell_tbl")
public class Sell {
	
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private Product product;

    private int quantity;
    
    private double amount;
    
    @ManyToOne
    private User user;
    
}
