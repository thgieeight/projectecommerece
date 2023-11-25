package com.bway.springproject.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.springproject.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findByCategory_Id(int categoryId);

}
