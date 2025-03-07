package com.accenture.Railway_Ticketing_System.admin;

import com.accenture.Railway_Ticketing_System.admin.AdminService;
import com.accenture.Railway_Ticketing_System.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(path = "/login")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String getUser() {
        return "admin";
    }

    @PostMapping("/admin")
    public String verifyUser(@ModelAttribute Admin admin, Model model) {
        return adminService.verifyUser(admin, model);
    }


}
