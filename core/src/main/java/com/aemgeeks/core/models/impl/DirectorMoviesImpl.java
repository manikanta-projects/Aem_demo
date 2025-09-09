package com.aemgeeks.core.models.impl;

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
}
