package com.aemgeeks.core.models.impl;

import com.aemgeeks.core.models.ServiceDemo;
import com.aemgeeks.core.services.DemoService;
import com.aemgeeks.core.services.DemoServiceA;
import com.aemgeeks.core.services.MultiService;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
       adapters = ServiceDemo.class,
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class ServiceDemoImpl implements ServiceDemo {

    @OSGiService(filter = "(component.name=com.aemgeeks.core.services.impl.MultiServiceAImpl)")
    MultiService multiService;

    @OSGiService(filter = "(component.name=serviceB)")
    MultiService multiServiceB;

    @OSGiService
    DemoService demoService;

    @Inject
    DemoServiceA demoServiceA;

    @Override
    public Iterator<Page> getPagesList() {
        return demoService.getPages();
    }

    @Override
    public List<String> getPagesTitleList() {
        return demoServiceA.getPages();
    }

    @Override
    public String getNameFromService() {
        return multiService.getNames();
    }

    @Override
    public String getNameFromServiceB() {
        return multiServiceB.getNames();
    }


    @Override
    public String getServiceResponse() {
        return "Response Coming From "+multiServiceB.getNames();
    }
}
