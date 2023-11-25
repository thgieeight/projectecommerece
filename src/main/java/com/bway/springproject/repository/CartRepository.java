package com.bway.springproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.springproject.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	

}
