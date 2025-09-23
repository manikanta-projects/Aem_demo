package com.aemgeeks.core.Utils;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import java.util.HashMap;
import java.util.Map;

public final class ResolverUtil {

    private ResolverUtil(){

    }

    public static final String GEEKS_SERVICE_USER = "geeksserviceuser";

    public static final ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory) throws LoginException{
        final Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put(ResourceResolverFactory.SUBSERVICE,GEEKS_SERVICE_USER);
        ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(paraMap);
        return resolver;
    }
}
