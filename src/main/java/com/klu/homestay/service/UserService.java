package com.klu.homestay.service;

import com.klu.homestay.model.User;

public interface UserService {
	public User checkUserlogin(String email,String password);
	public String userRegistration(User user);
	public boolean checkByEmailOrContact(String email, String phno);
	public void updateUserProfile(Long id, User updatedUser);
	public User getImage(Long id);
	public User getUserData(Long id);
	public User getCoverImage(Long id);
}