package com.bway.springproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.springproject.model.Sell;
import com.bway.springproject.model.User;

public interface SellRepository extends JpaRepository<Sell, Integer> {
	
    List<Sell> findByUser(User user);

}
