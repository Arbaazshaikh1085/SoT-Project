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
        if(user.getUsername().isEmpty()){
            System.out.println("Empty Username field");
            model.addAttribute("error", "Empty Username field");
            return "registration";
        }
        if(!user.getUsername().matches("^[a-zA-Z0-9_]+$")){
            System.out.println("Username contain numbers or special characters");
            model.addAttribute("error", "Username contain numbers or special characters only alphabets are valid");
            return "registration";
        }

        if(user.getFirstName().isEmpty()){
            System.out.println("Empty first Name field");
            model.addAttribute("error", "Empty first name field");
            return "registration";
        }
        if(!user.getFirstName().matches("[A-Za-z]+")){
            System.out.println("Firstname contain numbers or special characters");
            model.addAttribute("error", "First name contain numbers or special characters only alphabets are valid");
            return "registration";
        }

        if(user.getLastName().isEmpty()){
            System.out.println("Empty Last Name field");
            model.addAttribute("error", "Empty last name field");
            return "registration";
        }
        if(!user.getLastName().matches("[A-Za-z]+")){
            System.out.println("last name contain numbers or special characters");
            model.addAttribute("error", "Last name contain numbers or special characters only alphabets are valid");
            return "registration";
        }

        if(!user.getEmail().matches(".+@.+\\.(com|in|jp)")){
            System.out.println("Invalid email address");
            model.addAttribute("error", "Invalid email address");
            return "registration";
        }

        List<String> existingEmailUser = userRepository.findUserByEmail(user.getEmail());
        System.out.println(existingEmailUser);
        if(!existingEmailUser.isEmpty()){
            System.out.println("Email address already exists");
            model.addAttribute("error", "Email address already exists");
            return "registration";
        }

        if(!(user.getContactNumber().length() == 10) || !(user.getContactNumber().matches("[0-9]+"))){
            System.out.println("Invalid phone number");
            model.addAttribute("error", "Invalid phone number");
            return "registration";
        }

        if(user.getPassword().length() < 8 || user.getPassword().equals(user.getUsername()) || user.getPassword().equals(user.getFirstName())|| user.getPassword().equals(user.getLastName()) || !(user.getPassword().matches(".*[a-zA-z]+.*")) || !(user.getPassword().matches(".*[0-9]+.*")) || !(user.getPassword().matches(".*[@_\\.-]+.*"))){
            System.out.println("Password should have least 8 characters with 1 alphabet 1 digit and 1 special character");
            model.addAttribute("error", "Password should have least 8 characters with 1 alphabet 1 digit and 1 special character");
            return "registration";
        }


        else{
            userRepository.save(user);
            model.addAttribute("success", "Registration successful! Please log in.");
            return "redirect:/login";
        }


    }


}