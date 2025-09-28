package com.aemgeeks.core.services;

import java.util.List;

public interface OsgiFactoryConfig {

    public int getConfigID();
    public String getConfigName();
    public String getConfigUrl();
    public List<OsgiFactoryConfig> getOsgiConfigs();
}
