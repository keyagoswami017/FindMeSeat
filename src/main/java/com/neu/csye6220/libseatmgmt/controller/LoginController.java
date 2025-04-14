package com.neu.csye6220.libseatmgmt.controller;


import com.neu.csye6220.libseatmgmt.dto.LoginDTO;
import com.neu.csye6220.libseatmgmt.dto.RegisterDTO;
import com.neu.csye6220.libseatmgmt.model.User;
import com.neu.csye6220.libseatmgmt.service.interfaces.IAdminService;
import com.neu.csye6220.libseatmgmt.service.interfaces.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new RegisterDTO());
        return "register";
    }

    @PostMapping("/register/user")
    public String registerUser(@Valid @ModelAttribute("user") RegisterDTO user, BindingResult result ,Model model) {
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        // Check if the email already exists in either Admin/User
        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("error", "Email already exists, Please try with different email");
            return "register";
        }

        // Encode the password
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encodedPassword);
        newUser.setPhone(user.getPhone());

        userService.registerUser(newUser);

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new LoginDTO());
        return "login";
    }

    @PostMapping("/doLogin")
    public String login(@Valid @ModelAttribute("user") LoginDTO user, BindingResult result, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "login";
        }
        // Session fix
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        HttpSession newSession = request.getSession(true);
        if("user".equals(user.getRole())) {
            int userId = (int) userService.authenticate(user.getEmail(), user.getPassword()); // login for user
            System.out.println("User ID found : " + userId);
            if(userId > 0) {
                newSession.setAttribute("userId", userId);
                newSession.setAttribute("role", "user");
                return "redirect:/user-Home";
            }
        }
        else {
            int adminId = (int) adminService.authenticate(user.getEmail(), user.getPassword()); // login for admin
            System.out.println("Admin ID found : " + adminId);
            if (adminId > 0) {
                newSession.setAttribute("adminId", adminId);
                newSession.setAttribute("role", "admin");
                return "redirect:/admin-Home";
            }
        }

        model.addAttribute("user",new LoginDTO());
        model.addAttribute("error", "Invalid email or password");
        return "login";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/user-Home")
    public String userHome(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        int userId = (int) session.getAttribute("userId");
        model.addAttribute("user", userService.getUserById((long) userId));
        return "user-Home";
    }

    @GetMapping("/admin-Home")
    public String adminHome(HttpSession session, Model model) {
        if (session.getAttribute("adminId") == null) {
            return "redirect:/login";
        }
        int adminId = (int) session.getAttribute("adminId");
        model.addAttribute("user", adminService.getAdminById((long) adminId));
        return "admin-Home";
    }

}