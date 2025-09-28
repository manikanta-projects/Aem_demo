package com.aemgeeks.core.services.impl;

import com.aemgeeks.core.configs.GeeksOsgiFactoryConfig;
import com.aemgeeks.core.services.OsgiFactoryConfig;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;

import java.util.ArrayList;
import java.util.List;

@Component(service = OsgiFactoryConfig.class,immediate = true)
@Designate(ocd = GeeksOsgiFactoryConfig.class,factory = true)
public class OsgiFactoryConfigImpl implements OsgiFactoryConfig {

    private int configID;
    private String configName;
    private String configUrl;
    private List<OsgiFactoryConfig> configList;

    @Activate
    protected void activate(GeeksOsgiFactoryConfig geeksOsgiFactoryConfig){
        configID = geeksOsgiFactoryConfig.getConfigID();
        configName = geeksOsgiFactoryConfig.getConfigName();
        configUrl = geeksOsgiFactoryConfig.getConfigUrl();
    }

    @Reference(service = OsgiFactoryConfig.class,cardinality = ReferenceCardinality.MULTIPLE,policy = ReferencePolicy.DYNAMIC)
    public void bindOsgiFactoryConfig(final OsgiFactoryConfig config){
        if (configList==null){
                 configList = new ArrayList<>();
        }
        configList.add(config);

    }

    public void unbindOsgiFactoryConfig(final OsgiFactoryConfig config){
        configList.remove(config);
    }

    @Override
    public int getConfigID() {
        return configID;
    }

    @Override
    public String getConfigName() {
        return configName;
    }

    @Override
    public String getConfigUrl() {
        return configUrl;
    }

    @Override
    public List<OsgiFactoryConfig> getOsgiConfigs() {
        return configList;
    }
}
