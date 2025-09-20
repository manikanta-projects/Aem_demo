package com.aemgeeks.core.models.impl;

import com.aemgeeks.core.models.Author;
import com.day.cq.search.QueryBuilder;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Author.class,
        resourceType = AuthorImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

@Exporter(name = "jackson",extensions = "json",selector = "geeksdemo",
         options = {
                    @ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE", value = "true"),
                    @ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true")
         })

@JsonRootName(value = "author-details")
public class AuthorImpl implements Author {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorImpl.class);
    static final String RESOURCE_TYPE = "/apps/aemgeeks/components/content/Author";

    @ScriptVariable
    Page currentPage;

    @SlingObject
    ResourceResolver resourceResolver;

    @OSGiService
    QueryBuilder queryBuilder;

    @Self
    SlingHttpServletRequest slingHttpServletRequest;

    @Inject
    Resource resource;

    @RequestAttribute(name = "rAttribute")
    String reqAttribute;

    @ResourcePath(path = "/content/aemgeeks/us/en/home")@Via("resource")
    Resource resourcePage;

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

    @ValueMapValue
    List<String> authorbooks;

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
    @JsonIgnore
    public String getReqAttribute() {
        return reqAttribute;
    }

    @Override
    public String getHomePageName() {
        return resourcePage.getName();
    }

    @Override
    public String getLastModifiedBy() {
        return modifiedBy;
    }

    @JsonProperty(value = "author-name")
    public String authorName(){
        return "Manikanta";
    }

    @Override
    @JsonProperty(value = "books")
    public List<String> getAuthorBooks() {
         if (authorbooks!=null){
             return new ArrayList<String>(authorbooks);
         } else {
             return Collections.emptyList();
         }
    }

    @Override
    @JsonProperty(value = "books-details")
    public List<Map<String, String>> getBookDetailsWithMap() {
        List<Map<String,String>> bookDetailsMap = new ArrayList<>();
        try {
            Resource bookDetailMap = resource.getChild("bookdetailswithmap");
            if (bookDetailMap!=null){
                for (Resource books : bookDetailMap.getChildren()){
                    Map<String,String> bookMap = new HashMap<>();
                    bookMap.put("bookname",books.getValueMap().get("bookname",String.class));
                    bookMap.put("booksubject",books.getValueMap().get("booksubject", String.class));
                    bookMap.put("publishyear",books.getValueMap().get("publishyear",String.class));
                    bookDetailsMap.add(bookMap);
                }
            }
        } catch (Exception e) {
            LOG.info("\n Error While getting the Books : {}",e.getMessage());
        }

        return bookDetailsMap;
    }


    @PostConstruct
    protected void init(){
        LOG.info("\n Page Title : {} , Resource Path : {} ",currentPage.getTitle(),resourcePage.getPath());
    }
}
