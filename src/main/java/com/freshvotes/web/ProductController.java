package com.freshvotes.web;

import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String getProduct(ModelMap model) {
        return "product";
    }

    // We use the curly brackets here as place holder for the incoming productId
    @GetMapping("/products/{productId}")
    public String getProduct (@PathVariable Long productId) {
        return "product";
    }

    // Receive a post from the login page (`CreateProduct` button)
    // Whenever using PostMapping, we should use redirect
    @PostMapping("/products")
    // annotation here "@AuthenticationPrincipal" is a Spring magic
    // This is used to get the authentication whenever we need in the applicaiton
    public String createProduct(@AuthenticationPrincipal User user) {
        Product product = new Product();

        product.setPublished(false);
        product.setUser(user);

        // This might not work. Because Hibernate might not recognite the user that was passed in
        product = productRepository.save(product);

        // It's a good convension to return the details of results of the post method
        return "redirect:/products/" + product.getId();
    }
    
}
