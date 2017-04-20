package com.pointwest.workforce.planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pointwest.workforce.planner.domain.CustomError;
import com.pointwest.workforce.planner.domain.User;
import com.pointwest.workforce.planner.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, value="/users/{username}")
    public ResponseEntity<Object> fetchUser(@PathVariable String username) {
		User user = userService.fetchUser(username);
		if(user == null) {
			return new ResponseEntity<>(new CustomError("User not found"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
    }

}
