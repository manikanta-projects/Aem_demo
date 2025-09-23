package com.aemgeeks.core.services.impl;

import com.aemgeeks.core.Utils.ResolverUtil;
import com.aemgeeks.core.services.DemoService;
import com.aemgeeks.core.services.MultiService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;


@Component(service = DemoService.class,immediate = true)
public class DemoServiceImpl implements DemoService {
    private static final Logger LOG = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Reference(target = "(component.name=serviceB)")
    MultiService multiServiceB;

    @Override
    public Iterator<Page> getPages() {
        try {
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage("/content/aemgeeks/us/en");
            Iterator<Page> pages;
            pages = page.listChildren();
            return pages;
        } catch (LoginException e) {
            LOG.info("\n Error While Fetching the Pages Title : {}",e.getMessage());
        }
        return null;
    }

    @Override
    public String getServiceList() {
        return "Response Coming From "+multiServiceB.getNames();
    }
}
