package com.haivu.spring.jpa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
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
import com.haivu.spring.jpa.model.User.StatusUser;
import com.haivu.spring.jpa.repository.UserRepository;
import com.haivu.spring.jpa.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;
	
	List<User> users = new ArrayList<User>();
	
    @Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		Mockito.when(userService.getAllUser()).thenAnswer(new Answer<List<User>>() {
            public List<User> answer(InvocationOnMock invocation)
                    throws Throwable {
            	List<User> users = new ArrayList<User>();
                for (int i = 0; i < 5; i++) {
                    users.add(new User(1, "haivuit", "123456", "Hai Vu", new Date(), "haivu@gmail", StatusUser.ENABLE));
                }
                return users;
            }
        });
	}

	@Test
	public void getAllUserTest() {
		List<User> list = userService.getAllUser();
        Assert.assertTrue(list.size() == 1);
	}
	
	@Test
	public void addUserTest() {
		User user = new User();
		Mockito.when(userService.addNewUser(user)).thenReturn(null);
	}

}
