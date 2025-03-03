package com.accenture.Railway_Ticketing_System.user;

import com.accenture.Railway_Ticketing_System.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "")
public class UserController {



    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getUser() {
        return "login";
    }

    @PostMapping("/login")
    public String verifyUser(@ModelAttribute User user, Model model) {
        return userService.verifyUser(user, model);
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute User user, Model model){
        return userService.addNewUser(user, model);
    }



    @GetMapping("/homepage")
    public String homepage() {
        return "homepage";
    }



}
