package com.aemgeeks.core.services.impl;

import com.aemgeeks.core.services.MultiService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

@Component(service = MultiService.class,immediate = true,name = "serviceB")
@ServiceRanking(1001)
public class MultiServiceBImpl implements MultiService {

    @Override
    public String getNames() {
        return "MultiServiceBImpl";
    }
}
