package com.aemgeeks.core.models.impl;

import com.aemgeeks.core.models.DirectorMovies;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
       adapters = DirectorMovies.class,
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class DirectorMoviesImpl implements DirectorMovies {

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
}
