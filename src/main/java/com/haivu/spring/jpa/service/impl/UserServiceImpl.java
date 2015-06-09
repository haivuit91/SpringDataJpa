package com.haivu.spring.jpa.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haivu.spring.jpa.model.User;
import com.haivu.spring.jpa.repository.UserRepository;
import com.haivu.spring.jpa.service.UserService;

@Service(UserService.NAME)
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;

	@Override
	public Page<User> getAllUserAndPagination(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public User getUserById(int userId) {
		return userRepository.findOne(userId);
	}

	@Override
	@Transactional
	public User addNewUser(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User editUser(User user) {
		// User edit = userRepository.findOne(user.getUserId());
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User delUser(int userId) {
		User del = userRepository.findOne(userId);
		userRepository.delete(del);
		return del;
	}

}
