package com.example.beststore.controllers;

import com.example.beststore.dto.UserDto;
import com.example.beststore.entity.User;
import com.example.beststore.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AuthController {
    @Autowired

    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "products/register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        /*if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }*/

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "products/users";
    }
    // Trang quản lý sản phẩm cho ADMIN
    @RequestMapping("/admin/product_page")
    public String ProductPage() {
        return "products/admin";  // Trang quản lý sản phẩm (chỉ dành cho admin)
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "products/login"; // Tên file HTML của trang đăng nhập (login.html)
    }
}

