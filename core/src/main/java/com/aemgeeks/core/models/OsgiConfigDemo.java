package com.aemgeeks.core.models;

import com.aemgeeks.core.services.OsgiFactoryConfig;
import com.day.cq.dam.api.lightbox.LightboxService;

import java.util.List;

public interface OsgiConfigDemo {

    public String getServiceName();
    public int getServiceCount();
    public boolean getLiveData();
    public String[] getCountries();
    public String getRunModes();
    public int getServiceID();
    public String getServiceUrl();
    public String getModuleServiceName();
    public List<OsgiFactoryConfig> getAllOsgiConfigs();
 }
