package com.aemgeeks.core.models;

import com.aemgeeks.core.helper.MultifieldHelper;

import java.util.List;
import java.util.Map;

public interface DirectorMovies {

    String getDirectorName();
    List<String> getDirectorMovies();
    List<Map<String,String>> getDirectorMoviesDetails();
    List<MultifieldHelper> getMoviesDetailsBudget();
    List<MultifieldHelper> getMoviesSequelDetails();
}
