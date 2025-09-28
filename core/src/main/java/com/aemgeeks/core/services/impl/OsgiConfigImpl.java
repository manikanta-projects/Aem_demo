package com.aemgeeks.core.services.impl;

import com.aemgeeks.core.services.OsgiConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.*;

import javax.swing.plaf.PanelUI;

@Component(service = OsgiConfig.class,immediate = true)
@Designate(ocd = OsgiConfigImpl.serviceConfig.class)
public class OsgiConfigImpl implements OsgiConfig {

    @ObjectClassDefinition(name = "AEM GEEKS - Osgi Configuration",
                           description = "Osgi Configuration Demo")

    public @interface serviceConfig {

        @AttributeDefinition(
                name = "Service Name",
                description = "Provide Service Name",
                type = AttributeType.STRING)
        public String serviceName() default "DTDC Courier Service";

        @AttributeDefinition(
                name = "Service Count",
                description = "Provide Service Count",
                type = AttributeType.INTEGER)
        public int getServiceCount() default 150;

        @AttributeDefinition(
                name = "Live Data",
                description = "Whether it is Live Data?",
                type = AttributeType.BOOLEAN)
        public boolean getLiveData() default false;

        @AttributeDefinition(
                name = "Countries",
                description = "Operational Countries",
                type = AttributeType.STRING)
        public String[] getCountries() default {"india","Sri Lanka","China","Bangladesh"};

        @AttributeDefinition(
                name = "Run Modes",
                description = "Provide the Run Modes",
                options = {
                        @Option(label = "Author",value = "author"),
                        @Option(label = "Publish",value = "publish"),
                        @Option(label = "Both",value = "both")
                },
                type = AttributeType.STRING)
        public String getRunModes() default "Author";
    }

    private String serviceName;
    private int serviceCount;
    private boolean liveData;
    private String[] countries;
    private String runModes;

    @Activate
    protected void activate(serviceConfig config){
        serviceName = config.serviceName();
        serviceCount = config.getServiceCount();
        liveData = config.getLiveData();
        countries = config.getCountries();
        runModes = config.getRunModes();
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public int getServiceCount() {
        return serviceCount;
    }

    @Override
    public boolean getLiveData() {
        return liveData;
    }

    @Override
    public String[] getCountries() {
        return countries;
    }

    @Override
    public String getRunModes() {
        return runModes;
    }

}
