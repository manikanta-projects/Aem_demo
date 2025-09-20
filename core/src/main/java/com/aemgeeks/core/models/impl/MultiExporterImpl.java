package com.aemgeeks.core.models.impl;

import com.aemgeeks.core.models.MultiExporter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
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
       adapters = MultiExporter.class,
       resourceType = MultiExporterImpl.RESOURCE_TYPE,
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

@Exporters({
        @Exporter(name = "jackson",extensions = "json",selector = "geeksjson",options =
                {
                        @ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE", value = "true"),
                        @ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true")
                }),
        @Exporter(name = "geeksxml",extensions = "xml",selector = "geeksxml")
})

@XmlRootElement(name = "xml-exporter")
@JsonRootName("json-exporter")
public class MultiExporterImpl implements MultiExporter {
    private static final Logger LOG = LoggerFactory.getLogger(MultiExporterImpl.class);
    static final String RESOURCE_TYPE = "/apps/aemgeeks/components/content/Multi Exporters";

    @Inject
    private String title;

    @Inject
    private String description;

    @Inject
    private Calendar publishdate;

    @Inject
    List<String> authorbooks;

    @Override
    @JsonProperty(value = "json-title")
    @XmlElement(name = "xml-title")
    public String getTitle() {
        return title;
    }

    @Override
    @JsonProperty(value = "xml-description")
    @XmlElement(name = "xml-description")
    public String getDescription() {
        return description;
    }

    @Override
    @JsonProperty(value = "json-publishdate")
    @XmlElement(name = "xml-publishdate")
    public Calendar getPublishDate() {
        return publishdate;
    }

    @Override
    @XmlElement(name = "book")
    @JsonProperty(value = "json-authorbooks")
    @XmlElementWrapper(name = "xml-authorbooks")
    public List<String> getAuthorBooks() {
        if (authorbooks!=null){
            return new ArrayList<String>(authorbooks);
        } else {
            return Collections.emptyList();
        }
    }
}
