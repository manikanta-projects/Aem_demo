package com.aemgeeks.core.models.impl;

import com.aemgeeks.core.models.Author;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorImpl implements Author {

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
}
