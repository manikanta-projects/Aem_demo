package com.aemgeeks.core.models;

import java.util.List;
import java.util.Map;

public interface Author {

    String getFirstName();
    String getLastName();
    Boolean getIsProfessor();
    String getPageTitle();
    String getReqAttribute();
    String getHomePageName();
    String getLastModifiedBy();
    List<String> getAuthorBooks();
    List<Map<String,String>> getBookDetailsWithMap();
}
