package com.freshvotes.service;

import com.freshvotes.domain.Feature;
import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.repositories.FeatureRepository;
import com.freshvotes.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FeatureRepository featureRepository;

    public Feature createFeature(Long productId, User user) {
        Feature feature = new Feature();

        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            // bi-directional relationship
            feature.setProduct(product);
            product.getFeatures().add(feature);

            // bi-directional relationship
            feature.setUser(user);
            user.getFeatures().add(feature);

            feature.setStatus("Pending Review");

            // return and save the created feature
            return featureRepository.save(feature);
        }

        return feature;
    }

    public Feature save(Feature feature) {
        Feature resFeature = featureRepository.save(feature);

        return resFeature;
    }

    public Optional<Feature> findById(Long featureId) {
        return featureRepository.findById(featureId);
    }
}
