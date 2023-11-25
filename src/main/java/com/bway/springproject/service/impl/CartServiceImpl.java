package com.bway.springproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.Cart;
import com.bway.springproject.repository.CartRepository;
import com.bway.springproject.service.CartService;
@Service

public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository CartRepo;
	
	@Override
	public void addCart(Cart cart) {
		CartRepo.save(cart);
	}

	@Override
	public void deleteCart(int id) {
		CartRepo.deleteById(id);
	}

	@Override
	public void updateCart(Cart cart) {
		CartRepo.save(cart);
	}

	@Override
	public Cart getCartById(int id) {
		return CartRepo.findById(id).get();
	}

	@Override
	public List<Cart> getAllCart() {
		return CartRepo.findAll();
	}

	@Override
	public void deleteAllCarts() {
		CartRepo.deleteAll();
	}


}
