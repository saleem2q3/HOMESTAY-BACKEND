package com.klu.homestay.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Lob
    private byte[] profileImage;

    @Lob
    private byte[] coverImage; // Add coverImage as a byte array

    // Getters and setters for profileImage
    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    // Getters and setters for coverImage
    public byte[] getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }

    @Column(name = "first_name", nullable = false, length = 100)
    private String first_name;
    
    @Column(name = "last_name", nullable = false, length = 100)
    private String last_name;
    
    @Column(name = "user_email", nullable = false, length = 100, unique = true)
    private String email;
    
    @Column(name = "user_password", nullable = false, length = 100)
    private String password;
    
    @Column(name = "phno", nullable = false, length = 10)
    private String phno;

    // Getters and setters for other fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    // Update method to update coverImage along with other details
    public void updateDetails(User updatedUser) {
        if (updatedUser.getFirst_name() != null) {
            this.first_name = updatedUser.getFirst_name();
        }
        if (updatedUser.getLast_name() != null) {
            this.last_name = updatedUser.getLast_name();
        }
        if (updatedUser.getPhno() != null) {
            this.phno = updatedUser.getPhno();
        }
        if (updatedUser.getEmail() != null) {
            this.email = updatedUser.getEmail();
        }
        if (updatedUser.getProfileImage() != null) {
            this.profileImage = updatedUser.getProfileImage();
        }
        if (updatedUser.getCoverImage() != null) { // Update coverImage
            this.coverImage = updatedUser.getCoverImage();
        }
    }
}
