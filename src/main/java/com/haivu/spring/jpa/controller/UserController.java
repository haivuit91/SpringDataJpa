package com.haivu.spring.jpa.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haivu.spring.jpa.model.PageWrapper;
import com.haivu.spring.jpa.model.User;
import com.haivu.spring.jpa.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	@Qualifier(UserService.NAME)
	private UserService userSv;

	@Scope("prototype")
	public String setView(ModelMap model, String view) {
		model.put("views", view);
		return "common";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getListUser(ModelMap model, Pageable pageable, User user) {
		// PageWrapper<User> page = new PageWrapper<User>(
		// userSv.getAllUserAndPagination(pageable), "list");
		Page<User> pages = userSv.getAllUserAndPagination(pageable);
		List<User> listUser = userSv.getAllUser();
		model.put("page", pages);
		model.put("listUser", listUser);
		return setView(model, "list-user");
	}

	@RequestMapping(value = "edit-user/{userId}", method = RequestMethod.GET)
	public String editUser(@PathVariable Integer userId, ModelMap model,
			User user) {
		user = userSv.getUserById(userId);
		model.put("user", user);
		return setView(model, "edit-user");
	}

	@RequestMapping(value = "save-user", method = RequestMethod.POST)
	@Scope("prototype")
	public String saveUser(@Valid User user, BindingResult bindingResult,
			ModelMap model) throws Exception {

		// System.out.println("-----------");
		// System.out.println(user.getUserId());
		// System.out.println(user.getUserName());
		// System.out.println(user.getPwd());
		// System.out.println(user.getFullName());
		// System.out.println(user.getDateOfBirth());
		// System.out.println(user.getEmail());
		// System.out.println(user.getStatus());
		// System.out.println("-----------");

		int userId = user.getUserId();
		if (userId != 0) {
			if (bindingResult.hasErrors()) {
				System.out.println("Error edit");
				return "redirect:edit-user/" + userId;
			}
			System.out.println("ok edit");
			System.out.println(user.getDateOfBirth());
			userSv.editUser(user);
		} else {
			if (bindingResult.hasErrors()) {
				System.out.println("Error add");
				return setView(model, "list-user");
			}
			System.out.println("ok add");
			userSv.addNewUser(user);
		}
		System.out.println("Ok");
		return "redirect:list";
	}

	@RequestMapping(value = "del-user/{userId}", method = RequestMethod.DELETE)
	public @ResponseBody String delUser(@PathVariable Integer userId)
			throws Exception {
		System.out.println("Ok");
		userSv.delUser(userId);
		return "Delete user successfully";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

}
