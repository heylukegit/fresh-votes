package com.freshvotes.repositories;


import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// The repository is a interface, because we are utilizing the JpaRepository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // We only need to use the findBy convention and Jpa will do the rest sql job.
    // select * from product where product.user = user
    List<Product> findByUser(User user);

}