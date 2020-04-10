package com.freshvotes.web;

import com.freshvotes.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

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
    // @ModelAttribute is very important here.
    // thymeleaf field is the annotation in HTML side and the
    // ModelAttribute is the Java Spring side in the data transmission.
    public String registerPost (@ModelAttribute User user) {

        // Use "redirect" here to avoid submitting data again.
        // Always use redirect in Post method
        System.out.println(user.toString());

        return "redirect:/register";

    }
}
