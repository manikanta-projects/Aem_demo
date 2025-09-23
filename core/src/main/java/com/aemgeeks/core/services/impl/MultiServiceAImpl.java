package com.aemgeeks.core.services.impl;

import com.aemgeeks.core.services.MultiService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

@Component(service = MultiService.class,immediate = true)
@ServiceRanking(1000)
public class MultiServiceAImpl implements MultiService {

    @Override
    public String getNames() {
        return "MultiServiceAImpl";
    }
}
