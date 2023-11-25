package com.bway.springproject.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bway.springproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsernameAndPassword(String un, String psw);
	User findByUsername(String un);

	@Transactional
	@Modifying
	@Query("Update User set  password = :psw  WHERE email = :email")
	void updatePasswod(String psw, String email);
}
