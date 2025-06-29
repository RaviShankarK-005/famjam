package com.famjam.jwtUtil.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.famjam.jwtUtil.dbUtil.User;
import com.famjam.jwtUtil.userUtil.UserService;


@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/users", method=RequestMethod.GET)
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
}
