package com.springRest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springRest.Entity.User;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        // Retrieve the user from the database based on the entered username
        User user = userRepository.findByUsername(username);

        // If the user is not found or the entered password doesn't match the stored password, show an error message
        if (user == null || !isPasswordValid(password, user.getPassword(), user.getSalt())) {
            // Show an error message or redirect to the login page with an error parameter
            return "redirect:/login?error";
        }

        // Authentication successful, proceed to the user's dashboard or home page
        return "redirect:/dashboard";
    }

    // Utility method to validate the password
    private boolean isPasswordValid(String enteredPassword, String hashedPassword, String salt) {
        // Hash the entered password with the stored salt using the same hashing algorithm
        String hashedEnteredPassword = hashPassword(enteredPassword, salt);

        // Compare the hashed passwords
        return hashedEnteredPassword.equals(hashedPassword);
    }

    // Utility method for password hashing
    // ...
}
