package com.aemgeeks.core.services;

public interface OsgiConfig {

    public String getServiceName();
    public int getServiceCount();
    public boolean getLiveData();
    public String[] getCountries();
    public String getRunModes();
}
