package com.aemgeeks.core.models.impl;

import com.aemgeeks.core.models.XmlExporter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Model(adaptables = Resource.class,
       adapters = XmlExporter.class,
       resourceType = XmlExporterImpl.RESOURCE_TYPE,
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

@Exporter(name = "geeksxml",extensions = "xml",selector = "geeksapi")
@XmlRootElement(name = "author-details")
public class XmlExporterImpl implements XmlExporter {
    private static final Logger LOG = LoggerFactory.getLogger(XmlExporterImpl.class);
    static final String RESOURCE_TYPE = "/apps/aemgeeks/components/content/Xml Exporter";

    @Inject
    private String title;

    @Inject
    private String description;

    @Inject
    private Calendar publishdate;

    @Inject
    List<String> authorbooks;

    @Override
    @XmlElement(name = "author-title")
    public String getTitle() {
        return title;
    }

    @Override
    @XmlElement(name = "author-description")
    public String getDescription() {
        return description;
    }

    @Override
    @XmlElement(name = "publish-date")
    public Calendar getPublishDate() {
        return publishdate;
    }

    @Override
    @XmlElementWrapper(name = "author-books")
    @XmlElement(name = "book")
    public List<String> getAuthorBooks() {
        if (authorbooks!=null){
            return new ArrayList<String>(authorbooks);
        } else {
            return Collections.emptyList();
        }
    }
}
