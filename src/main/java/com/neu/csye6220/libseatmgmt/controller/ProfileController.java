package com.neu.csye6220.libseatmgmt.controller;

import com.neu.csye6220.libseatmgmt.model.Admin;
import com.neu.csye6220.libseatmgmt.model.User;
import com.neu.csye6220.libseatmgmt.service.interfaces.IAdminService;
import com.neu.csye6220.libseatmgmt.service.interfaces.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IAdminService adminService;

    @GetMapping
    public String getProfile(HttpSession session,Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        Integer adminId = (Integer) session.getAttribute("adminId");
        if(userId == null && adminId == null) {
            return "redirect:/login";
        }

        if(userId != null){
            User user = userService.getUserById(Long.valueOf(userId));
            model.addAttribute("user",user);
        }
        if(adminId != null){
            Admin admin = adminService.getAdminById(Long.valueOf(adminId));
            model.addAttribute("user",admin);
        }
        return "profile";
    }



    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("user") User user, HttpSession session, RedirectAttributes redirectAttributes, HttpServletResponse response) {
        try {
            // Disable caching
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);

            Integer userId = (Integer) session.getAttribute("userId");
            Integer adminId = (Integer) session.getAttribute("adminId");
            if (userId == null && adminId == null) {
                return "redirect:/login";
            }
            if (userId != null) {
                User existingUser = userService.getUserById(Long.valueOf(userId));
                existingUser.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                existingUser.setPhone(user.getPhone());
                User updatedUser = userService.updateUser(existingUser);
                redirectAttributes.addFlashAttribute("user", updatedUser);
            }
            if (adminId != null) {
                Admin existingAdmin = adminService.getAdminById(Long.valueOf(adminId));
                existingAdmin.setFirstName(user.getFirstName());
                existingAdmin.setLastName(user.getLastName());
                existingAdmin.setPhone(user.getPhone());
                Admin updatedAdmin = adminService.updateAdmin(existingAdmin);
                redirectAttributes.addFlashAttribute("user", updatedAdmin);
            }

            redirectAttributes.addFlashAttribute("success", "Profile updated successfully!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update profile. Please try again.");
        }
        return "redirect:/profile";
    }

}
