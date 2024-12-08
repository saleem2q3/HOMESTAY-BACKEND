package com.klu.homestay.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.klu.homestay.model.User;
import com.klu.homestay.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    @Autowired
    UserService userservice;

	    @PostMapping(value = "/userRegistration", consumes = {"multipart/form-data"})
	    public ResponseEntity<String> registerUser(
	            @RequestParam("firstName") String firstName,
	            @RequestParam("lastName") String lastName,
	            @RequestParam("phno") String phno,
	            @RequestParam("email") String email,
	            @RequestParam("password") String password,
	            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
	            @RequestParam(value = "coverImage", required = false) MultipartFile coverImage) {
	
	        byte[] profileImageBytes = null;
	        byte[] coverImageBytes = null;
	
	        if (profileImage != null && !profileImage.isEmpty()) {
	            try {
	                profileImageBytes = profileImage.getBytes();
	            } catch (IOException e) {
	                return ResponseEntity.badRequest().body("Failed to process profile image");
	            }
	        }
	
	        if (coverImage != null && !coverImage.isEmpty()) {
	            try {
	                coverImageBytes = coverImage.getBytes();
	            } catch (IOException e) {
	                return ResponseEntity.badRequest().body("Failed to process cover image");
	            }
	        }
	
	        User user = new User();
	        user.setFirst_name(firstName);
	        user.setLast_name(lastName);
	        user.setPhno(phno);    
	        user.setEmail(email);
	        user.setPassword(password);
	        user.setProfileImage(profileImageBytes);
	        user.setCoverImage(coverImageBytes);
	
	        userservice.userRegistration(user);
	
	        return ResponseEntity.ok("User registered successfully.");
	    }

    @GetMapping("/checkUserExists")
    public boolean checkByEmailOrContact(@RequestParam String email, @RequestParam String phno) {
        return userservice.checkByEmailOrContact(email, phno);
    }

    @PostMapping("/userlogin")
    public ResponseEntity<User> checkUserlogin(@RequestBody Map<String, String> userLoginData, HttpSession session) {
        String email = userLoginData.get("email");
        String password = userLoginData.get("password");

        User user = userservice.checkUserlogin(email, password);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            session.setAttribute("userEmail", user.getEmail());
            session.setMaxInactiveInterval(30 * 60); // Timeout after 30 minutes
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("/getuserDetails")
    public ResponseEntity<User> getuserDetails(HttpSession session) {
        session.setMaxInactiveInterval(30 * 10);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); 
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/updateUserProfile", consumes = "multipart/form-data")
    public ResponseEntity<String> updateUserProfile(
            @RequestParam("first_name") String first_name,
            @RequestParam("last_name") String last_name,
            @RequestParam("phno") String phno,
            @RequestParam("email") String email,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestParam(value = "coverImage", required = false) MultipartFile coverImage,
            HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please log in to update your profile.");
        }

        try {
            user.setFirst_name(first_name);
            user.setLast_name(last_name);  
            user.setPhno(phno);
            user.setEmail(email);

            if (profileImage != null && !profileImage.isEmpty()) {
                byte[] profileImageBytes = profileImage.getBytes();
                user.setProfileImage(profileImageBytes);
            }

            if (coverImage != null && !coverImage.isEmpty()) {
                byte[] coverImageBytes = coverImage.getBytes();
                user.setCoverImage(coverImageBytes);
            }

            userservice.updateUserProfile(user.getId(), user); // Assume a userService handles the update.
            session.setAttribute("user", user); // Update session with the modified user object.

            return ResponseEntity.ok("Profile updated successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload images.");
        }
    }

    @GetMapping("/profile/{id}/image")
    public ResponseEntity<byte[]> getUserImage(@PathVariable Long id) {
        User user = userservice.getImage(id);
        byte[] image = user.getProfileImage();    
        return ResponseEntity.ok().body(image);
    }

    @GetMapping("/profile/{id}/coverImage")
    public ResponseEntity<byte[]> getCoverImage(@PathVariable Long id) {
        User user = userservice.getCoverImage(id); 
        byte[] coverImage = user.getCoverImage();
        return ResponseEntity.ok().body(coverImage);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully.");
    }
}
