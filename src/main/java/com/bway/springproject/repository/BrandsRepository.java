package com.bway.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.springproject.model.Brands;

public interface BrandsRepository extends JpaRepository<Brands, Integer> {

}
