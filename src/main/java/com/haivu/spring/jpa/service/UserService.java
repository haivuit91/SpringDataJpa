package com.haivu.spring.jpa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.haivu.spring.jpa.model.User;

public interface UserService {

	public static final String NAME = "userService";

	List<User> getAllUser();

	Page<User> getAllUserAndPagination(Pageable pageable);

	Iterable<User> getAllUserAndSort(Sort sort);

	User getUserById(int userId);

	User addNewUser(User user);

	User editUser(User user) throws Exception;

	User delUser(int userId) throws Exception;

}
