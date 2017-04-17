package com.pointwest.workforce.planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.User;
import com.pointwest.workforce.planner.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/users/{username}")
    public User fetchUser(@PathVariable String username) {
       return userService.fetchUser(username);
    }

}
