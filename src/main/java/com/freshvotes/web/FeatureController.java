package com.freshvotes.web;

import com.freshvotes.domain.Feature;
import com.freshvotes.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    /*
    POST createFeature will create a new feature and redirect to
    GET getFeature. We can see the details of the feature now.
    POST updateFeature will update the feature and redirect to GET getFeature
     */

    @PostMapping("")  // this maps to "/products/{prodcutId}/features"
    public String createFeature(@PathVariable Long productId) {
        Feature feature = featureService.createFeature(productId);

        return "redirect:/products/"+productId+"/features/"+feature.getId();
    }

    @GetMapping("/{featureId}")
    public String getFeature(ModelMap model, @PathVariable Long productId, @PathVariable Long featureId){

        Optional<Feature> featureOpt = featureService.findById(featureId);
        if (featureOpt.isPresent()) {
            model.put("feature", featureOpt.get());
        }
        // todo handle the situation that we cannot find a feature by featureId

        return "feature";
    }

    @PostMapping("/{featureId}")
    public String updateFeature(Feature feature, @PathVariable Long productId, @PathVariable Long featureId) {
        feature = featureService.save(feature);

        return "redirect:/products/"+productId+"/features/"+feature.getId();
    }

}
