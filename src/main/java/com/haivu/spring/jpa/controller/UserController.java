package com.haivu.spring.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haivu.spring.jpa.model.User;
import com.haivu.spring.jpa.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	@Qualifier(UserService.NAME)
	private UserService userSv;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getListUser(ModelMap model) {
		List<User> listUser = userSv.getAllUser();
		model.put("listUser", listUser);
		return "list-user :: resultsList";
	}

}
