package controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final InMemoryUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(InMemoryUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("firstName") String firstName,
                               @RequestParam("middleName") String middleName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {
        // Check if the email (as username) already exists
        if (userDetailsManager.userExists(email)) {
            return "redirect:/register?error"; // If user already exists
        }

        // Here, you could save additional user details to a database if needed.

        userDetailsManager.createUser(org.springframework.security.core.userdetails.User.withUsername(email) // Using email as username
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build());

        return "redirect:/login"; // Redirect to login after successful registration
    }
}
