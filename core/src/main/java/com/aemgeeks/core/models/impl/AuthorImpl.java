package com.aemgeeks.core.models.impl;

import com.aemgeeks.core.models.Author;
import com.day.cq.search.QueryBuilder;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorImpl implements Author {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorImpl.class);

    @ScriptVariable
    Page currentPage;

    @SlingObject
    ResourceResolver resourceResolver;

    @OSGiService
    QueryBuilder queryBuilder;

    @Self
    SlingHttpServletRequest slingHttpServletRequest;

    @RequestAttribute(name = "rAttribute")
    String reqAttribute;

    @ResourcePath(path = "/content/aemgeeks/us/en/home")@Via("resource")
    Resource resource;

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL,name = "jcr:lastModifiedBy")
    private String modifiedBy;

    @Inject
    @Via("resource")
    @Default(values = "Badiginjala")
    private String fname;

    @ValueMapValue
    @Default(values = "Srinivasulu")
    @Required
    private String lname;

    @Inject
    @Via("resource")
    private boolean isProfessor;

    @Override
    public String getFirstName() {
        return fname;
    }

    @Override
    public String getLastName() {
        return lname;
    }

    @Override
    public Boolean getIsProfessor() {
        return isProfessor;
    }

    @Override
    public String getPageTitle() {
        return currentPage.getTitle() ;
    }

    @Override
    public String getReqAttribute() {
        return reqAttribute;
    }

    @Override
    public String getHomePageName() {
        return resource.getName();
    }

    @Override
    public String getLastModifiedBy() {
        return modifiedBy;
    }

    @PostConstruct
    protected void init(){
        LOG.info("\n Page Title : {} , Resource Path : {} ",currentPage.getTitle(),resource.getPath());
    }
}
