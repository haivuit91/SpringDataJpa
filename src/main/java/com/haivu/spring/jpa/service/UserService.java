package com.haivu.spring.jpa.service;

import java.util.List;

import com.haivu.spring.jpa.model.User;

public interface UserService {

	public static final String NAME = "userService";

	List<User> getAllUser();

	User getUserById(int userId);

	User addNewUser(User user);

	User editUser(User user) throws Exception;

	User delUser(int userId) throws Exception;

}
