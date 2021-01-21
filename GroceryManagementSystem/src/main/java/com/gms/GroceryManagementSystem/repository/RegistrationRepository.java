package com.gms.GroceryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gms.GroceryManagementSystem.DAO.User;

public interface RegistrationRepository extends JpaRepository<User, Integer> {
	public User findByEmailId(String emailId);
	
	public User findByEmailIdAndPassword(String email,String password);
}
