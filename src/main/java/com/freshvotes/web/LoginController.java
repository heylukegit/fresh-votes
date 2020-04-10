package com.freshvotes.web;

import com.freshvotes.domain.User;
import com.freshvotes.repositories.UserRepository;
import com.freshvotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";  // correspond to the login HTML
    }


    @GetMapping("/register")
    public String register(ModelMap model) {
        model.put("user", new User());  // put a new User to the HTML page
        return "register";
    }

    @PostMapping("/register")
    // thymeleaf field is the annotation in HTML side and the
    // ModelAttribute is the Java Spring side in the data transmission.
    // But, in fact, this annotation can be omitted and Spring can still do it's job.
    public String registerPost (@ModelAttribute User user) {

        userService.save(user);

        // Use "redirect" here to avoid submitting data again.
        // Always use redirect in Post method
        return "redirect:/login";

    }
}
