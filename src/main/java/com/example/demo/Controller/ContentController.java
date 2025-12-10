package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.MyAppUser;
import com.example.demo.Model.MyAppUserRepositry;

@Controller
public class ContentController {

    @Autowired
    private MyAppUserRepositry userRepo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/complaint")
    public String complaint() {
        return "/complaint";
    }   

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
            @RequestParam String password) {

        Optional<MyAppUser> userOptional = userRepo.findByEmail(email);

        if (userOptional.isPresent()) {
            MyAppUser user = userOptional.get(); // get the actual user object

            if (user.getPassword().equals(password)) {
                return "redirect:/index"; // SUCCESS
            }
        }

        return "redirect:/login?error=true"; // FAILED
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
