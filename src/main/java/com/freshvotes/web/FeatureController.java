package com.freshvotes.web;

import com.freshvotes.domain.Feature;
import com.freshvotes.domain.User;
import com.freshvotes.service.FeatureService;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {
    Logger log = LoggerFactory.getLogger(FeatureController.class);

    @Autowired
    private FeatureService featureService;

    /*
    POST createFeature will create a new feature and redirect to
    GET getFeature. We can see the details of the feature now.
    POST updateFeature will update the feature and redirect to GET getFeature
     */

    @PostMapping("")  // this maps to "/products/{prodcutId}/features"
    public String createFeature(@AuthenticationPrincipal User user, @PathVariable Long productId) {
        Feature feature = featureService.createFeature(productId, user);

        return "redirect:/products/"+productId+"/features/"+feature.getId();
    }

    @GetMapping("/{featureId}")
    public String getFeature(@AuthenticationPrincipal User user, ModelMap model, @PathVariable Long productId, @PathVariable Long featureId){

        Optional<Feature> featureOpt = featureService.findById(featureId);
        if (featureOpt.isPresent()) {
            Feature feature = featureOpt.get();
            model.put("feature", feature);
            model.put("comments", feature.getComments());
        }
        // todo handle the situation that we cannot find a feature by featureId
        model.put("user", user);

        return "feature";
    }

    @PostMapping("/{featureId}")
    public String updateFeature(@AuthenticationPrincipal User user, Feature feature, @PathVariable Long productId, @PathVariable Long featureId) {
        feature.setUser(user);
        feature = featureService.save(feature);

        String encodedName = null;
        try {
            encodedName = URLEncoder.encode(feature.getProduct().getName(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.warn("Cannot encode the productName: "+feature.getProduct().getName()+
                     ", redirecting to dashboard insetead of intented product feature page. ");
            return "redirect:/dashboard";
        }

        return "redirect:/p/"+encodedName;
    }

}
