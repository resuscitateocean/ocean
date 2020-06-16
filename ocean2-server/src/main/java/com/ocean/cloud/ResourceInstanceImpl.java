package com.ocean.cloud;



import java.util.HashMap;
import java.util.Map;

import com.ocean.servlet.ResourceInstance;

public class ResourceInstanceImpl  implements ResourceInstance {

    Map<Class,Object> resourcesMap = new HashMap<>();

    @Override
    public Map<Class, Object> getResourceInstances() {
        return resourcesMap;
    }
}
