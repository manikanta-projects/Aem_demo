package com.aemgeeks.core.models;

import java.util.Calendar;
import java.util.List;

public interface MultiExporter {

    String getTitle();
    String getDescription();
    Calendar getPublishDate();
    List<String> getAuthorBooks();
}
