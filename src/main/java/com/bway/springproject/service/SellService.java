package com.bway.springproject.service;

import java.util.List;

import com.bway.springproject.model.Sell;
import com.bway.springproject.model.User;

public interface SellService {
	
	void addSell(Sell sell);

	void deleteSell(int id);
	
	void updateSell(Sell sell);

	Sell getSellById(int id);

	List<Sell> getAllSell();

    List<Sell> getSellsByUser(User user); // Add this method

}
