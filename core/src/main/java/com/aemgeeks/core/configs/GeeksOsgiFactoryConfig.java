package com.aemgeeks.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "AEM GEEKS - Osgi Factory Configuration",
        description = "Osgi Factory Configuration Demo")

public @interface GeeksOsgiFactoryConfig {

    @AttributeDefinition(
            name = "Config ID",
            description = "Provide Config ID",
            type = AttributeType.INTEGER)
    public int getConfigID() default 1475;

    @AttributeDefinition(
            name = "Service Name",
            description = "Provide Service Name",
            type = AttributeType.STRING)
    public String getConfigName() default "FaceBook";

    @AttributeDefinition(
            name = "Service Url",
            description = "Provide Service Url",
            type = AttributeType.STRING)
    public String getConfigUrl() default "https://www.facebook.com/";
}
