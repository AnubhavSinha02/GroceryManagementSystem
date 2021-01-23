package com.gms.GroceryManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gms.GroceryManagementSystem.DAO.User;
import com.gms.GroceryManagementSystem.service.RegistrationService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/registeruser")
	@CrossOrigin(origins="http://localhost:4200")
	public User registerUser(@RequestBody User user) throws Exception{
		String tempEmailId = user.getEmailId();
		if(tempEmailId != null && !"".equalsIgnoreCase(tempEmailId)) {
			User userobj=service.fetchUserByEmailId(tempEmailId);
			if(userobj!=null) {
				throw new Exception("User with "+tempEmailId+" already exists");
			}
		}
		User userObj=null;
		userObj=service.saveUser(user);
		return userObj;
	}
	
	@PostMapping("/login")
	@CrossOrigin(origins="http://localhost:4200")
	public User loginUser(@RequestBody User user) throws Exception{
		String tempEmailId = user.getEmailId();
		String tempPassword = user.getPassword();
		User userobj= null;
		if(tempEmailId != null && tempPassword !=null) {
			userobj=service.fetchUserByEmailIdAndPassword(tempEmailId, tempPassword);
		}
		if(userobj==null) {
			throw new Exception("Bad Credentials");
		}
		return userobj;
	}

}
