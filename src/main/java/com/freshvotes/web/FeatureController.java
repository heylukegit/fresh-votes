package com.freshvotes.web;

import com.freshvotes.domain.Feature;
import com.freshvotes.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping("/")  // this maps to "/products/{prodcutId}/features"
    public String createFeature(@PathVariable Long productId) {
        Feature feature = featureService.createFeature(productId);

        return "redirect:/products/"+productId+"/features/"+feature.getId();
    }

    @GetMapping("/{featureId}")
    public String getFeature(@PathVariable Long productId, @PathVariable Long featureId){
        return "feature";  // todo add more content
    }
}
