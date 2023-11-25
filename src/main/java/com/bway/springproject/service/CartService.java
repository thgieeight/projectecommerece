package com.bway.springproject.service;

import java.util.List;

import com.bway.springproject.model.Cart;


public interface CartService {

	void addCart(Cart cart);

	void deleteCart(int id);
	
    void deleteAllCarts();  // New method to delete all cart items


	void updateCart(Cart cart);

	Cart getCartById(int id);

	List<Cart> getAllCart();
	
}
