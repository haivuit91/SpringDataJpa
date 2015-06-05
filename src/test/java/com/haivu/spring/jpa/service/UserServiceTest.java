package com.haivu.spring.jpa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.haivu.spring.jpa.model.User;
import com.haivu.spring.jpa.repository.UserRepository;
import com.haivu.spring.jpa.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	private Map<Integer, User> userMap = new HashMap<Integer, User>();

	private Map<Integer, User> createUserMap(int length) {
		Map<Integer, User> quesMap = new HashMap<Integer, User>();
		for (int i = 0; i < length; i++) {
			User question = createUser(i, "User Name" + i, "Password" + i,
					"Full Name" + i, new Date(), "Email" + i,
					User.StatusUser.ENABLE);
			quesMap.put(i, question);
		}
		return quesMap;
	}

	private User createUser(int userId, String userName, String pwd,
			String fullName, Date dateOfBirth, String email,
			User.StatusUser status) {
		User user = new User(userId, userName, pwd, fullName, dateOfBirth,
				email, status);
		return user;
	}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userMap = createUserMap(10);
		Mockito.when(userRepository.findAll()).thenAnswer(
				new Answer<List<User>>() {
					public List<User> answer(InvocationOnMock invocation)
							throws Throwable {
						List<User> listUser = new ArrayList<User>();
						for (int i = 0; i < userMap.size(); i++) {
							listUser.add(userMap.get(i));
						}
						return listUser;
					}
				});
	}

	@Test
	public void getAllUserTest() {

	}

}
