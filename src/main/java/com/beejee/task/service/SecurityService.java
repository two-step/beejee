package com.beejee.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beejee.task.model.User;
import com.beejee.task.repository.IUserRepository;

@Service
public class SecurityService {
	@Autowired
	private IUserRepository userRepo;
	
	public boolean checkAdmin(User user) {
		User admin = userRepo.findByLoginAndPassword(user.getLogin(), user.getPassword()); 
		if(admin != null) {
			return true;
		}
		return false;
	}
	
}
