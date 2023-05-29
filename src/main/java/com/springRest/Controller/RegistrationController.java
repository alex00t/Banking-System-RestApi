package com.springRest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springRest.Entity.User;
import com.springRest.Repositary.UserRepository;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        // Save the user to the database
        userRepository.save(user);

        return "redirect:/login";

    // Utility methods for salt generation and password hashing
    // ...

    private String generateSalt() {
        // Generate a random salt value
        // Example implementation using Apache Commons Codec:
        return RandomStringUtils.randomAlphanumeric(16);
    }

    private String hashPassword(String password, String salt) {
        // Hash the password with the salt using a secure hashing algorithm
        // Example implementation using Spring Security's BCryptPasswordEncoder:
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password + salt);
    }
}
