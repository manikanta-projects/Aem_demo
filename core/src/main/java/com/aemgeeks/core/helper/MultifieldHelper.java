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
    private String movieName;
    private String moviePlot;
    private Date releaseDate;
    private Integer movieBudget;
    private List<NastedHelper> bookEditions;
    private List<NastedHelper> movieSequels;
    public MultifieldHelper(Resource resource){
        try {
            if(StringUtils.isNotBlank(resource.getValueMap().get("bookname", String.class))){
                this.bookName = resource.getValueMap().get("bookname", String.class);
            }
            if (StringUtils.isNotBlank(resource.getValueMap().get("movieplot", String.class))){
                this.moviePlot = resource.getValueMap().get("movieplot", String.class);
            }
            if (StringUtils.isNotBlank(resource.getValueMap().get("booksubject", String.class))){
                this.bookSubject = resource.getValueMap().get("booksubject", String.class);
            }
            if (StringUtils.isNotBlank(resource.getValueMap().get("moviename", String.class))){
                this.movieName = resource.getValueMap().get("moviename", String.class);
            }
            if (resource.getValueMap().get("publishdate", Date.class)!=null){
                this.publishDate = resource.getValueMap().get("publishdate", Date.class);
            }
            if (resource.getValueMap().get("releasedate", Date.class)!=null){
                this.releaseDate = resource.getValueMap().get("releasedate", Date.class);
            }
            if(resource.getValueMap().get("copies", Integer.class)!=null){
                this.copies = resource.getValueMap().get("copies", Integer.class);
            }
            if (resource.getValueMap().get("moviebudget", Integer.class)!=null){
                this.movieBudget = resource.getValueMap().get("moviebudget", Integer.class);
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

    public String getMovieName(){
        return movieName;
    }

    public String getMoviePlot(){
        return moviePlot;

    }
    public Date getReleaseDate(){
        return releaseDate;
    }

    public Integer getMovieBudget(){
        return movieBudget;
    }
    public List<NastedHelper> getBookEditions(){
        return bookEditions;
    }

    public List<NastedHelper> getMovieSequels(){
        return movieSequels;
    }

    public void setMovieSequels(List<NastedHelper> movieSequels){
        this.movieSequels = movieSequels;
    }

    public void setBookEditions(List<NastedHelper> bookEditions){
        this.bookEditions = bookEditions;
    }
}
