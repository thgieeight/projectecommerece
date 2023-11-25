package com.bway.springproject.service;

import java.util.List;

import com.bway.springproject.model.User;

public interface IUserService {

	void signup(User user);
	User login(String un, String psw);
	User isUserExists(String un);
	List<User> getAllInfo(); 


}
