package com.bway.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.springproject.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
