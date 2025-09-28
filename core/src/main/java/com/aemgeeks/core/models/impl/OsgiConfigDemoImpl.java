package com.aemgeeks.core.models.impl;

import com.aemgeeks.core.models.OsgiConfigDemo;
import com.aemgeeks.core.services.OsgiConfig;
import com.aemgeeks.core.services.OsgiConfigModule;
import com.aemgeeks.core.services.OsgiFactoryConfig;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
      adapters = OsgiConfigDemo.class,
      defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class OsgiConfigDemoImpl implements OsgiConfigDemo {

    @OSGiService
    OsgiConfig osgiConfig;

    @OSGiService
    OsgiConfigModule osgiConfigModule;

    @Inject
    OsgiFactoryConfig osgiFactoryConfig;

    @Override
    public String getServiceName() {
        return osgiConfig.getServiceName();
    }

    @Override
    public int getServiceCount() {
        return osgiConfig.getServiceCount();
    }

    @Override
    public boolean getLiveData() {
        return osgiConfig.getLiveData();
    }

    @Override
    public String[] getCountries() {
        return osgiConfig.getCountries();
    }

    @Override
    public String getRunModes() {
        return osgiConfig.getRunModes();
    }

    @Override
    public int getServiceID() {
        return osgiConfigModule.getServiceID();
    }

    @Override
    public String getServiceUrl() {
        return osgiConfigModule.getServiceUrl();
    }

    @Override
    public String getModuleServiceName() {
        return osgiConfigModule.getServiceNameModule();
    }

    @Override
    public List<OsgiFactoryConfig> getAllOsgiConfigs() {
        return osgiFactoryConfig.getOsgiConfigs();
    }
}
