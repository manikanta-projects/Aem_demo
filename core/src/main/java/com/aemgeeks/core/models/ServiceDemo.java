package com.aemgeeks.core.models;

import com.day.cq.wcm.api.Page;

import java.util.Iterator;
import java.util.List;

public interface ServiceDemo {

    public Iterator<Page> getPagesList();
    public List<String> getPagesTitleList();
    public String getNameFromService();
    public String getNameFromServiceB();
    public String getServiceResponse();
}
