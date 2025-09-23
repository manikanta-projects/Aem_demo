package com.aemgeeks.core.models.impl;


import com.aemgeeks.core.models.LoggerDemo;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
       adapters = LoggerDemo.class,
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class LoggerDemoImpl implements LoggerDemo {
    private static final Logger LOG = LoggerFactory.getLogger(LoggerDemoImpl.class);

    @Override
    public List<String> getPagesTitle() {
        return null;
    }

    @PostConstruct
    protected void init(){
        LOG.trace("\n ============= PRINTING TRACE ============");
        LOG.debug("\n ============= PRINTING DEBUG ============");
        LOG.info("\n ============= PRINTING INFO ============");
        LOG.warn("\n ============= PRINTING WARN ============");
        LOG.error("\n ============= PRINTING ERROR ============");
    }
}
