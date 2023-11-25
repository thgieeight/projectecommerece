package com.bway.springproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Sell;
import com.bway.springproject.model.User;
import com.bway.springproject.repository.SellRepository;
import com.bway.springproject.service.SellService;

@Service
public class SellServiceImpl implements SellService {

	@Autowired
	private SellRepository sellRepo;
	
	@Override
	public void addSell(Sell sell) {
		sellRepo.save(sell);
	}

	@Override
	public void deleteSell(int id) {
		sellRepo.deleteById(id);		
	}

	@Override
	public void updateSell(Sell sell) {
		sellRepo.save(sell);
		
	}

	@Override
	public Sell getSellById(int id) {
		return sellRepo.findById(id).get();

	}


	@Override
	public List<Sell> getSellsByUser(User user) {
        return sellRepo.findByUser(user);
	}

	@Override
	public List<Sell> getAllSell() {
	    return sellRepo.findAll();
	}

}
