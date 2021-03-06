package com.freshvotes.web;

import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    // a logger
    private Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    // We use the curly brackets here as place holder for the incoming productId
    @GetMapping("/products/{productId}")
    // ModelMap is used to pass the product info into frontend
    public String getProduct (@PathVariable Long productId,
                              ModelMap modelMap,
                              HttpServletResponse response) throws IOException {

        // Use the findByIdWithUser() method
        Optional<Product> optionalProduct = productRepository.findByIdWithUser(productId);

        // If the query didn't return any product.
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            modelMap.put("product", product);
        } else {
            // show a 404 not found error and a message on the page
            response.sendError(HttpStatus.NOT_FOUND.value(),
                    "Product with id " + productId.toString() + " was not found");
            return "product";
        }
        return "product";
    }

    @GetMapping("/p/{productName}")  // use p here to disambiguous different GetMapping
    public String productUserView(@PathVariable String productName, ModelMap model) {
        if (productName != null) {
            try {
                String decodedProductName = URLDecoder.decode(productName, StandardCharsets.UTF_8.name());
                Optional<Product> productOpt = productRepository.findByName(decodedProductName);

                if (productOpt.isPresent()) {
                    model.put("product", productOpt.get());
                }

            } catch (UnsupportedEncodingException e) {
                log.error("There was an error decoding a productName URL", e);
            }
        }

        return "productUserView";
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

        // This might not work. Because Hibernate might not recognite the user that was passed in。
        // But it turned out to be working.
        product = productRepository.save(product);

        // It's a good convension to return the details of results of the post method
        return "redirect:/products/" + product.getId();
    }

    @PostMapping("/products/{productId}")
    // We can use product directly because we use thymeleaf to assign the values in the frontend
    public String saveProduct(@PathVariable Long productId, Product product) {

        System.out.println(product.toString());

        // If save method is invoke without an id, it's an insert.
        // Otherwise, it's an update.
        product = productRepository.save(product);

        return "redirect:/products/" + product.getId();
    }

}
