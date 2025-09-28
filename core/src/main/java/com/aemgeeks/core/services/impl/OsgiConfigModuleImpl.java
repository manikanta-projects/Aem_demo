package com.aemgeeks.core.services.impl;

import com.aemgeeks.core.configs.GeeksOsgiConfig;
import com.aemgeeks.core.services.OsgiConfigModule;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = OsgiConfigModule.class,immediate = true)
@Designate(ocd = GeeksOsgiConfig.class)
public class OsgiConfigModuleImpl implements OsgiConfigModule {

    private int serviceID;
    private String serviceNameModule;
    private String serviceUrl;

    @Activate
    protected void activate(GeeksOsgiConfig geeksOsgiConfig){
        serviceID = geeksOsgiConfig.serviceID();
        serviceNameModule = geeksOsgiConfig.getServiceName();
        serviceUrl = geeksOsgiConfig.getServiceUrl();
    }

    @Override
    public int getServiceID() {
        return serviceID;
    }

    @Override
    public String getServiceNameModule() {
        return serviceNameModule;
    }

    @Override
    public String getServiceUrl() {
        return serviceUrl;
    }
}
