package com.beejee.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beejee.task.model.User;
import com.beejee.task.service.SecurityService;

@RestController
@CrossOrigin(origins = "*")
public class SecurityController {
	@Autowired
	private SecurityService securityService;
	
	@PostMapping("/taskapi/login")
	public boolean authenticate(@RequestBody User user) {
		return securityService.checkAdmin(user);
	}
}
