package com.freshvotes.repositories;


import com.freshvotes.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// The repository is a interface, because we are utilizing the JpaRepository
public interface ProductRepository extends JpaRepository<Product, Long> {

}