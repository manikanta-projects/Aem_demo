package com.aemgeeks.core.services.impl;

import com.aemgeeks.core.services.DemoService;
import com.aemgeeks.core.services.DemoServiceA;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component(service = DemoServiceA.class,immediate = true)
public class DemoServiceAImpl implements DemoServiceA{
    private static final Logger LOG = LoggerFactory.getLogger(DemoServiceA.class);

    @Reference
    DemoService demoService;

    @Override
    public List<String> getPages() {
        List<String> listpages = new ArrayList<String>();
        try {
            Iterator<Page> pages = demoService.getPages();
            while (pages.hasNext()){
                listpages.add(pages.next().getTitle());
            }
            return listpages;
        } catch (Exception e) {
            LOG.info("\n Error While Fetching Pages : {}",e.getMessage());
        }
        return null;
    }
}
