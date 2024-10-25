package controller;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {


        @Autowired
        private UserRepository userRepository;

        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

        @PostMapping("/register")
        public String registerUser(@RequestParam("firstName") String firstName,
                                   @RequestParam("middleName") String middleName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   Model model) {

            // Check if user already exists
            if (userRepository.findByEmail(email) != null) {
                model.addAttribute("error", "Email is already registered.");
                return "register";
            }

            // Create new user and encode password
            User user = new User();
            user.setFirstName(firstName);
            user.setMiddleName(middleName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));

            // Save user
            userRepository.save(user);

            model.addAttribute("success", "User registered successfully. Please login.");
            return "login"; // Redirect to login page after successful registration
        }
    }

