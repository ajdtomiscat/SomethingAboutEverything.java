
package com.example.secondhand.controller;

import com.example.secondhand.entity.User;
import com.example.secondhand.service.CategoryService;
import com.example.secondhand.service.ProductService;
import com.example.secondhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ProductService productService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }
    
    @GetMapping("/testdb")
    public String testDb(Model model) {
        try {
            List<com.example.secondhand.entity.Category> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("count", categories != null ? categories.size() : 0);
            return "testdb";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "testdb";
        }
    }
    
    @GetMapping("/")
    public String index(Model model) {
        try {
            List<com.example.secondhand.entity.Category> categories = categoryService.findAll();
            List<com.example.secondhand.entity.Product> products = productService.findAll();
            
            if (categories == null) {
                categories = java.util.Collections.emptyList();
            }
            if (products == null) {
                products = java.util.Collections.emptyList();
            }
            
            model.addAttribute("categories", categories);
            model.addAttribute("products", products);
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, 
                       HttpSession session, Model model) {
        try {
            User user = userService.login(username, password);
            session.setAttribute("user", user);
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password,
                          @RequestParam String nickname, @RequestParam(required = false) String phone,
                          Model model) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setNickname(nickname);
            user.setPhone(phone);
            userService.register(user);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam String nickname, @RequestParam(required = false) String phone,
                               @RequestParam(required = false) String email, @RequestParam(required = false) String address,
                               HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        user.setNickname(nickname);
        user.setPhone(phone);
        user.setEmail(email);
        user.setAddress(address);
        userService.update(user);
        session.setAttribute("user", user);
        return "redirect:/profile";
    }
}
