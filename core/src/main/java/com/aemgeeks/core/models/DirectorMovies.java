package com.aemgeeks.core.models;

import java.util.List;
import java.util.Map;

public interface DirectorMovies {

    String getDirectorName();
    List<String> getDirectorMovies();
    List<Map<String,String>> getDirectorMoviesDetails();
}
