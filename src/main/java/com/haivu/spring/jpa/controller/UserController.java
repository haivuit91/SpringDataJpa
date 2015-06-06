package com.haivu.spring.jpa.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haivu.spring.jpa.model.User;
import com.haivu.spring.jpa.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	@Qualifier(UserService.NAME)
	private UserService userSv;

	@Scope("prototype")
	public String setView(Integer page, Integer size, ModelMap model,
			String view) {
		int pageDefault = (page != null) ? page - 1 : 0;
		int sizeDefault = (size != null) ? size : 5;

		Page<User> userPage = userSv.getAllUserAndPagination(new PageRequest(
				pageDefault, sizeDefault, new Sort(new Order(Direction.DESC,
						"userId"))));

		int current = userPage.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, userPage.getTotalPages());

		model.put("listUser", userPage);
		model.put("beginIndex", begin);
		model.put("endIndex", end);
		model.put("currentIndex", current);
		model.put("views", view);
		return "common";
	}

	// ?page=1&size=5&sort=userId,desc
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getListUser(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			ModelMap model, User user) {
		return setView(page, size, model, "list-user");
	}

	@RequestMapping(value = "edit-user/{userId}", method = RequestMethod.GET)
	public String editUser(@PathVariable Integer userId, ModelMap model,
			User user) {
		user = userSv.getUserById(userId);
		model.put("user", user);
		model.put("views", "edit-user");
		return "common";
	}

	@RequestMapping(value = "save-user", method = RequestMethod.POST)
	@Scope("prototype")
	public String saveUser(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@Valid User user, BindingResult bindingResult, ModelMap model)
			throws Exception {

		int userId = user.getUserId();
		if (userId != 0) {
			if (bindingResult.hasErrors()) {
				System.out.println("Error edit");
				model.put("views", "edit-user");
				return "redirect:edit-user/" + userId;
			}
			System.out.println("ok edit");
			System.out.println(user.getDateOfBirth());
			userSv.editUser(user);
		} else {
			if (bindingResult.hasErrors()) {
				System.out.println("Error add");
				return setView(page, size, model, "list-user");
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
