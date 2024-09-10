package com.lcwd.user.service.Services;

import java.util.List;

import com.lcwd.user.service.entities.User;

public interface UserService {

	//user operations
	//create user
	User saveUser(User user);
	
	//get all user
	
	List<User> getAllUser();
	
	//get single user using given user id
	
	User getUser(String userId);
	
	//ToDo
	//delete
	//update
}
