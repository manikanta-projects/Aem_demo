package com.aemgeeks.core.models;

import com.aemgeeks.core.helper.MultifieldHelper;
import com.aemgeeks.core.helper.NastedHelper;

import java.util.List;
import java.util.Map;

public interface AuthorBooks {

    String getAuthorName();
    List<String> getAuthorBooks();
    List<Map<String,String>> getBookDetailsWithMap();
    List<MultifieldHelper> getBookDetailsWithBean();
    List<MultifieldHelper> getBookDetailsWithNastedMultifield();

}
