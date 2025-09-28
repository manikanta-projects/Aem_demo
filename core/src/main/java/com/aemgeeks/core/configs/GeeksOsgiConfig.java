package com.aemgeeks.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "AEM GEEK - Modular Osgi Configuration",
                       description = "Modular Osgi Configuration Demo")
public @interface GeeksOsgiConfig {

    @AttributeDefinition(
            name = "Service ID",
            description = "Provide the Service ID",
            type = AttributeType.INTEGER)
    public int serviceID() default 146943;

    @AttributeDefinition(
            name = "Service Name",
            description = "Provide Service Name",
            type = AttributeType.STRING)
    public String getServiceName() default "Bookmyshow";

    @AttributeDefinition(
            name = "Service Url",
            description = "Provide Service Url",
            type = AttributeType.STRING)
    public String getServiceUrl() default "https://in.bookmyshow.com/";
}
