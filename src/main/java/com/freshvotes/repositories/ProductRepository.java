package com.freshvotes.repositories;


import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// The repository is a interface, because we are utilizing the JpaRepository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // We only need to use the findBy convention and Jpa will do the rest sql job.
    // select * from product where product.user = user
    List<Product> findByUser(User user);

    // We use a query here to cope with the problem that the user attr of a product will
    // become null whenever we update the product.
    // The problem is caused by Lazy fetch setting between User and Product.
    // But we don't want to change the Lazy setting because it saves lots of resources.
    // We use this join fetch method to override Lazy setting.
    @Query("select p from Product p join fetch p.user where p.id = :id")
    Optional<Product> findByIdWithUser(Long id);

    Optional<Product> findByName(String name);

}