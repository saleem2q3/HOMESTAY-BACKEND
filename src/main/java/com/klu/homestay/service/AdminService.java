package com.klu.homestay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.homestay.model.Admin;
import com.klu.homestay.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin adminLogin(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }
}
