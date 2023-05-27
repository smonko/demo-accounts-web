package stefanmonko.sk.accountsweb.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import stefanmonko.sk.accountsweb.FeatureProperties;

@Controller
public class FeaturesController {

    @Autowired
    private FeatureProperties featureFlags;

    @RequestMapping("/features")
    public String features(Model model) {

        Map <String, Boolean> featuresValues = new HashMap<>();
        featuresValues.put("Canary Flag", featureFlags.getCanaryflag());
        featuresValues.put("Data Stream", featureFlags.getDatastream());
        featuresValues.put("DB Data", featureFlags.getDbdata());
        featuresValues.put("Messaging", featureFlags.getMessaging());

        model.addAttribute("features", featuresValues);

        return "features";

    }
    
}
