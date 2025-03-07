package com.accenture.Railway_Ticketing_System.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;


import java.util.*;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public String verifyUser(Admin admin, Model model) {
        System.out.println("Verifying admin: " + admin.getUsername());
        Optional<Admin> adminOptional = adminRepository.findUserByUsername(admin.getUsername());

        if (adminOptional.isPresent()) {
            Admin existingAdmin = adminOptional.get();
            System.out.println("Admin found: " + existingAdmin.getUsername());

            // Check if the username matches the password
            if (existingAdmin.getPassword().equals(admin.getPassword())) {
                // If yes, redirect to homepage.html
                System.out.println("Password matches");
                return "redirect:/homepage";
            } else {
                // Else, send message saying wrong password, return to same page
                System.out.println("Wrong password");
                model.addAttribute("error", "Wrong password, please try again.");
                return "admin";
            }
        } else {
            // Show userId not found and return to same page
            System.out.println("Admin ID not found");
            model.addAttribute("error", "Admin ID not found, please try again.");
            return "admin";
        }
    }
}
