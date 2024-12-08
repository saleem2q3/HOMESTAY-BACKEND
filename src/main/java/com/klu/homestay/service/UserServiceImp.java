package com.klu.homestay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.homestay.model.User;
import com.klu.homestay.repository.UserRepository;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	UserRepository userrepository;

	@Override
	public User checkUserlogin(String email, String password) {
		
		return userrepository.checkUserLogin(email, password);
	}

	@Override
	public String userRegistration(User user) {
		userrepository.save(user);
		return "user registered successfully...";
		
	}

	@Override
	public boolean checkByEmailOrContact(String email, String phno) {
		return userrepository.findByEmail(email).isPresent() || userrepository.findByPhno(phno).isPresent();
	}

	@SuppressWarnings("deprecation")
	@Override
	public User getImage(Long id) {
		return userrepository.getById(id);
	}

	public void updateUserProfile(Long id, User updatedUser) {
	    User existingUser = userrepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("User not found"));

	    // Assuming User entity has a method to update specific fields
	    existingUser.updateDetails(updatedUser);

	    // Save the updated entity
	    userrepository.save(existingUser);
	}

	@SuppressWarnings("deprecation")
	@Override
	public User getUserData(Long id) {
		
		return userrepository.getById(id);
	}

	 @Override
	    public User getCoverImage(Long id) {
	        // You might want to implement specific logic here for fetching cover image
	        User user = userrepository.findById(id).orElse(null);
	        if (user != null) {
	            return user; // Return the user object with cover image
	        }
	        return null;
	    }

}