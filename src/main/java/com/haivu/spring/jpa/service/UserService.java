package com.haivu.spring.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.haivu.spring.jpa.model.User;

public interface UserService {

	public static final String NAME = "userService";

	Page<User> getAllUserAndPagination(Pageable pageable);

	User getUserById(int userId);

	User addNewUser(User user);

	User editUser(User user) throws Exception;

	User delUser(int userId) throws Exception;

}
