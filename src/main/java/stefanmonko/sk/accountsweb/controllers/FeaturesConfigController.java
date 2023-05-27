package stefanmonko.sk.accountsweb.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stefanmonko.sk.accountsweb.FeatureProperties;


@RestController
public class FeaturesConfigController {

    @Autowired
    private FeatureProperties featureFlags;

    @RequestMapping(path = "/feature-flags", produces="application/json")
    public Map<String, String> showFeatures() {
    HashMap<String, String> map = new HashMap<>();

    map.put("canaryflag", featureFlags.getCanaryflag().toString());
    map.put("datastream", featureFlags.getDatastream().toString());
    map.put("dbdata", featureFlags.getDbdata().toString());
    map.put("messaging", featureFlags.getMessaging().toString());

    return map;

    }
        
}
