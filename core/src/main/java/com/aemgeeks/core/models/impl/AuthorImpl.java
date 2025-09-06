package com.aemgeeks.core.models.impl;

import com.aemgeeks.core.models.Author;
import com.day.cq.search.QueryBuilder;
import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;

import javax.inject.Inject;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorImpl implements Author {

    @ScriptVariable
    Page currentPage;

    @SlingObject
    ResourceResolver resourceResolver;

    @OSGiService
    QueryBuilder queryBuilder;

    @Self
    SlingHttpServletRequest slingHttpServletRequest;

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
}
