package com.accenture.Railway_Ticketing_System.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;


import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String verifyUser(User user, Model model) {
        System.out.println("Verifying user: " + user.getUsername());
        Optional<User> userOptional = userRepository.findUserByUsername(user.getUsername());

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            System.out.println("User found: " + existingUser.getUsername());

            // Check if the username matches the password
            if (existingUser.getPassword().equals(user.getPassword())) {
                // If yes, redirect to homepage.html
                System.out.println("Password matches");
                return "redirect:/ticket";
            } else {
                // Else, send message saying wrong password, return to same page
                System.out.println("Wrong password");
                model.addAttribute("error", "Wrong password, please try again.");
                return "login";
            }
        } else {
            // Show userId not found and return to same page
            System.out.println("User ID not found");
            model.addAttribute("error", "User ID not found, please try again.");
            return "login";
        }
    }

    public String addNewUser(User user, Model model) {
        System.out.println("Verifying user: " + user.getUsername());
        Optional<User> userOptional = userRepository.findUserByUsername(user.getUsername());

        if (userOptional.isPresent()) {
            System.out.println("Username already exist found");
            model.addAttribute("error", "username Taken, please try different username");
            return "registration";
        }

        userRepository.save(user);
        model.addAttribute("success", "Registration successful! Please log in.");
        return "redirect:/login";
    }
}