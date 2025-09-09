package com.aemgeeks.core.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class NastedHelper {
    private static final Logger LOG = LoggerFactory.getLogger(NastedHelper.class);
    private Integer bookEdition;
    private Date editionDate;
    private String movieTitle;
    private Date releaseTime;
    public NastedHelper(Resource resource){
        try {
            if (StringUtils.isNotBlank(resource.getValueMap().get("movietitle",String.class))){
                this.movieTitle = resource.getValueMap().get("movietitle", String.class);
            }
            if (resource.getValueMap().get("releasetime", Date.class)!=null){
                this.releaseTime = resource.getValueMap().get("releasetime", Date.class);
            }
            if (resource.getValueMap().get("bookedition", Integer.class)!=null){
                this.bookEdition = resource.getValueMap().get("bookedition", Integer.class);
            }
            if (resource.getValueMap().get("editiondate", Date.class)!=null){
                this.editionDate = resource.getValueMap().get("editiondate", Date.class);
            }
        } catch (Exception e) {
            LOG.info("\n Error Getting the Book Editions : {}",e.getMessage());
        }
    }

    public Integer getBookEdition(){
        return bookEdition;
    }

    public Date getEditionDate(){
        return editionDate;
    }

    public String getMovieTitle(){
        return movieTitle;
    }

    public Date getReleaseTime(){
        return releaseTime;
    }
}
