package com.aemgeeks.core.models.impl;

import com.aemgeeks.core.helper.MultifieldHelper;
import com.aemgeeks.core.helper.NastedHelper;
import com.aemgeeks.core.models.DirectorMovies;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
       adapters = DirectorMovies.class,
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class DirectorMoviesImpl implements DirectorMovies {
    private static final Logger LOG = LoggerFactory.getLogger(DirectorMoviesImpl.class);

    @Inject
    Resource resource;

    @ValueMapValue
    private String directorname;

    @ValueMapValue
    private List<String> directormovies;

    @Override
    public String getDirectorName() {
        return directorname;
    }

    @Override
    public List<String> getDirectorMovies() {
        if (directormovies!=null){
            return new ArrayList<String>(directormovies);
        }else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Map<String, String>> getDirectorMoviesDetails() {
        List<Map<String,String>> moviesDetails = new ArrayList<>();
        try {
            Resource moviesDetail = resource.getChild("moviesdetails");
            if (moviesDetail!=null){
                for (Resource movies : moviesDetail.getChildren()){
                    Map<String,String> moviesMap = new HashMap<>();
                    moviesMap.put("moviename",movies.getValueMap().get("moviename", String.class));
                    moviesMap.put("movieplot",movies.getValueMap().get("movieplot", String.class));
                    moviesMap.put("releasedyear",movies.getValueMap().get("releasedyear", String.class));
                    moviesDetails.add(moviesMap);
                }
            }
        } catch (Exception e) {
            LOG.info("\n Error While Getting the Movies Details : {}",e.getMessage());
        }
        LOG.info("\n SIZE : {}",moviesDetails.size());
        return moviesDetails;
    }

    @Override
    public List<MultifieldHelper> getMoviesDetailsBudget() {
        List<MultifieldHelper> moviesBudget = new ArrayList<>();
        try {
            Resource movieBudget = resource.getChild("moviesdetailsbudget");
            if (movieBudget!=null){
                for (Resource budget : movieBudget.getChildren()){
                    LOG.info("\n Movie name : {}",budget.getValueMap().get("moviename",String.class));
                    LOG.info("\n Movie Budget : {}",budget.getValueMap().get("moviebudget",Integer.class));
                    moviesBudget.add(new MultifieldHelper(budget));
                }
            }
        } catch (Exception e) {
            LOG.info("\n Error While Getting Movie Budget Details : {}",e.getMessage());
        }
        LOG.info("\n MOVIES SIZE : {}",moviesBudget.size());
        return moviesBudget;
    }

    @Override
    public List<MultifieldHelper> getMoviesSequelDetails() {
        List<MultifieldHelper> movieSequelDetails = new ArrayList<>();
        try {
            Resource movieSequels = resource.getChild("moviessequeldetails");
            if (movieSequels!=null){
                for (Resource movieSequel : movieSequels.getChildren()){
                    MultifieldHelper multifieldHelper = new MultifieldHelper(movieSequel);
                    if (movieSequel.hasChildren()){
                        List<NastedHelper> movieSequelListDetails = new ArrayList<>();
                        Resource movieSequelsList = movieSequel.getChild("moviesequels");
                        for (Resource movieSequelDetail : movieSequelsList.getChildren()){
                                  movieSequelListDetails.add(new NastedHelper(movieSequelDetail));
                        }
                        multifieldHelper.setMovieSequels(movieSequelListDetails);
                    }
                    movieSequelDetails.add(multifieldHelper);
                }
            }
        } catch (Exception e) {
            LOG.info("\n Error While Getting Movie Sequel Details : {}",e.getMessage());
        }
        return movieSequelDetails;
    }
}
