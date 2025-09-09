package com.aemgeeks.core.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class MultifieldHelper {
    private static final Logger LOG = LoggerFactory.getLogger(MultifieldHelper.class);
    private String bookName;
    private String bookSubject;
    private Date publishDate;
    private Integer copies;
    private List<NastedHelper> bookEditions;
    public MultifieldHelper(Resource resource){
        try {
            if(StringUtils.isNotBlank(resource.getValueMap().get("bookname", String.class))){
                this.bookName = resource.getValueMap().get("bookname", String.class);
            }
            if (StringUtils.isNotBlank(resource.getValueMap().get("booksubject", String.class))){
                this.bookSubject = resource.getValueMap().get("booksubject", String.class);
            }
            if (resource.getValueMap().get("publishdate", Date.class)!=null){
                this.publishDate = resource.getValueMap().get("publishdate", Date.class);
            }
            if(resource.getValueMap().get("copies", Integer.class)!=null){
                this.copies = resource.getValueMap().get("copies", Integer.class);
            }
        } catch (Exception e) {
            LOG.info("\n Bean Error : {}",e.getMessage());
        }
    }

    public String getBookName(){
        return bookName;
    }

    public String getBookSubject(){
        return bookSubject;
    }

    public Date getPublishDate(){
        return publishDate;
    }

    public Integer getCopies(){
        return copies;
    }

    public List<NastedHelper> getBookEditions(){
        return bookEditions;
    }

    public void setBookEditions(List<NastedHelper> bookEditions){
        this.bookEditions = bookEditions;
    }
}
