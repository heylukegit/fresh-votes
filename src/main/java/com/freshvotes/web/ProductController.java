package com.freshvotes.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @GetMapping("/products")
    public String getProduct(ModelMap model) {
        return "product";
    }

    // Receive a post from the login page (`CreateProduct` button)
    // Whenever using PostMapping, we should use redirect
    @PostMapping("/products")
    public String createProduct() {
        return "redirect:/products";
    }


}
